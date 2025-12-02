package gabriel.fontes.br.quarkus.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import gabriel.fontes.br.quarkus.Dto.ClienteResponse;
import gabriel.fontes.br.quarkus.Dto.ItemPedidoRequest;
import gabriel.fontes.br.quarkus.Dto.PedidoRequest;
import gabriel.fontes.br.quarkus.Dto.PedidoResponse;
import gabriel.fontes.br.quarkus.Model.Cliente;
import gabriel.fontes.br.quarkus.Model.Endereco;
import gabriel.fontes.br.quarkus.Model.EnderecoEntrega;
import gabriel.fontes.br.quarkus.Model.Fonte;
import gabriel.fontes.br.quarkus.Model.ItemPedido;
import gabriel.fontes.br.quarkus.Model.Pedido;
import gabriel.fontes.br.quarkus.Model.Enums.TipoPagamento;
import gabriel.fontes.br.quarkus.Repository.ClienteRepository;
import gabriel.fontes.br.quarkus.Repository.EnderecoRepository;
import gabriel.fontes.br.quarkus.Repository.FonteRepository;
import gabriel.fontes.br.quarkus.Repository.PedidoRepository;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {
    
    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    FonteRepository fonteRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    ClienteService clienteService;

    @Inject
    JsonWebToken jwt;

    @Override
    @Transactional
    public PedidoResponse create(PedidoRequest dto) {

        // 2. Validar e obter dados do cliente autenticado
        ClienteResponse clienteResponse = clienteService.getMeuPerfil();
        
        // 1. Obter dados do usuário autenticado via Keycloak
        String idUsuarioKeycloak = jwt.getSubject();

        // Buscar o cliente autenticado no banco de dados
        Cliente clienteAutenticado = clienteRepository.findByIdKeycloak(idUsuarioKeycloak);
        
        // Verificar se o cliente autenticado foi encontrado
        if (clienteAutenticado == null) {
            throw new ForbiddenException("Cadastro de cliente não encontrado para o usuário autenticado.");
        }

        // Extrair outras informações do token JWT, se necessário
        String emailUsuarioKeycloak = jwt.getClaim("email");
        System.out.println("Email do usuário autenticado: " + emailUsuarioKeycloak);
        String nomeUsuarioKeycloak = jwt.getClaim("name");
        
        // Validar o endereço de entrega
        Endereco enderecoBanco = enderecoRepository.findByIdOptional(dto.idEnderecoEntrega())
                .orElseThrow(() -> new NotFoundException("Endereço não encontrado com id: " + dto.idEnderecoEntrega()));

        // Verificar se o endereço pertence ao cliente autenticado
        if (!enderecoBanco.getPessoa().getId().equals(clienteAutenticado.getId())) {
            throw new ForbiddenException("O endereço informado não pertence ao cliente autenticado.");
        }

        // 3. Mapear Pedido
        Pedido pedido = new Pedido();
        pedido.setIdUsuario(idUsuarioKeycloak);

        pedido.setNomeClienteSnapshot(clienteAutenticado.getNome());
        pedido.setData(LocalDateTime.now());
       

        // 4. Mapear Endereço (Snapshot)
        EnderecoEntrega enderecoEntrega = new EnderecoEntrega();
        enderecoEntrega.setRua(enderecoBanco.getRua());
        enderecoEntrega.setNumero(enderecoBanco.getNumero());
        enderecoEntrega.setComplemento(enderecoBanco.getComplemento());
        enderecoEntrega.setBairro(enderecoBanco.getBairro());
        enderecoEntrega.setCidade(enderecoBanco.getCidade());
        enderecoEntrega.setEstado(enderecoBanco.getEstado());
        enderecoEntrega.setCep(enderecoBanco.getCep());
        pedido.setEnderecoEntrega(enderecoEntrega);

        // 5. Pagamento (Adicionado tratamento de erro)
        try {
            pedido.setTipoPagamento(TipoPagamento.valueOf(dto.tipoPagamento().toUpperCase()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Tipo de pagamento inválido: " + dto.tipoPagamento());
        }

        // 6. Mapear Itens e Calcular Total
        double total = 0.0;
        List<ItemPedido> itensParaSalvar = new ArrayList<>();

       
        // Corrigido para usar itemDto dentro do loop
        for (ItemPedidoRequest itemDto : dto.itensPedido()) {
            
            // Buscar a fonte pelo ID fornecido
            Fonte fonte = fonteRepository.findByIdOptional(itemDto.fonteId())
                    .orElseThrow(() -> new BadRequestException("Fonte não encontrada: " + itemDto.fonteId()));

            // Verificar estoque
            if (fonte.getEstoque() < itemDto.quantidade()) {
                throw new BadRequestException("Estoque insuficiente para a fonte: " + fonte.getNome());
            }
            
            // Baixar estoque
            fonte.setEstoque(fonte.getEstoque() - itemDto.quantidade());

            // Mapear ItemPedido
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setFonte(fonte);
            itemPedido.setQuantidade(itemDto.quantidade());
            itemPedido.setPreco(fonte.getPreco());
            itemPedido.setPedido(pedido);

            // CORREÇÃO: Usar itemDto.quantidade() (variável) e não ItemPedidoResponse.quantidade() (classe)
            total += fonte.getPreco() * itemDto.quantidade();
            itensParaSalvar.add(itemPedido);
        }

        // 7. Finalizar Pedido
        pedido.setTotal(total);      
        pedido.setItens(itensParaSalvar);

        pedidoRepository.persist(pedido);
        
        return PedidoResponse.fromEntity(pedido);
    }

    @Override
    @Transactional
    public List<PedidoResponse> findAll() {
        
        // Buscar todos os pedidos
        List<Pedido> pedidos = pedidoRepository.listAll();
        return pedidos.stream()
                .map(PedidoResponse::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public PedidoResponse findById(Long id) {

        // Buscar o pedido pelo ID e lançar exceção se não encontrado
        Pedido pedido = pedidoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado com id: " + id));
        return PedidoResponse.fromEntity(pedido);
    }

    @Override
    @Transactional
    public PedidoResponse delete(Long id) {

        // Buscar o pedido pelo ID e lançar exceção se não encontrado
        Pedido pedido = pedidoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado com id: " + id));
        
                // Excluir o pedido e retornar a resposta
                PedidoResponse resposta = PedidoResponse.fromEntity(pedido);
                pedidoRepository.delete(pedido);
                return resposta;
    }

    @Override
    @Transactional
    public PedidoResponse update(Long id, PedidoRequest dto) {

        // Buscar o pedido pelo ID e lançar exceção se não encontrado
        Pedido pedido = pedidoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado com id: " + id));
        
        pedido.setNomeClienteSnapshot(dto.nomeCliente());
        pedido.setTotal(dto.total());
        // Atualizar outros campos conforme necessário

        return PedidoResponse.fromEntity(pedido);
    }

    @Override
    public List<PedidoResponse> MeusPedidos() {

        // Obter o ID do usuário autenticado via Keycloak
        String idUsuarioKeycloak = jwt.getSubject();

        // Buscar os pedidos associados ao usuário autenticado
        List<Pedido> pedidosDoUsuario = pedidoRepository.findByUsuarioId(idUsuarioKeycloak);

        // Converter a lista de entidades Pedido para uma lista de DTOs PedidoResponse
        return pedidosDoUsuario.stream()
                .map(PedidoResponse::fromEntity)
                .toList();
    }
        
}
