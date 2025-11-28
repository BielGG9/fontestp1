package gabriel.fontes.br.quarkus.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;


import gabriel.fontes.br.quarkus.Dto.ItemPedidoRequest;
import gabriel.fontes.br.quarkus.Dto.PedidoRequest;
import gabriel.fontes.br.quarkus.Dto.PedidoResponse;
import gabriel.fontes.br.quarkus.Model.Endereco;
import gabriel.fontes.br.quarkus.Model.EnderecoEntrega;
import gabriel.fontes.br.quarkus.Model.Fonte;
import gabriel.fontes.br.quarkus.Model.ItemPedido;
import gabriel.fontes.br.quarkus.Model.Pedido;
import gabriel.fontes.br.quarkus.Model.Enums.TipoPagamento;
import gabriel.fontes.br.quarkus.Repository.EnderecoRepository;
import gabriel.fontes.br.quarkus.Repository.FonteRepository;
import gabriel.fontes.br.quarkus.Repository.PedidoRepository;
import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    JsonWebToken jwt;

    @Override
    @Transactional
    public PedidoResponse create(PedidoRequest dto) {
        
        // 1. Obter dados do usuário autenticado via Keycloak
        String idUsuarioKeycloak = jwt.getSubject();
        String emailUsuarioKeycloak = jwt.getClaim("email");
        System.out.println("Email do usuário autenticado: " + emailUsuarioKeycloak);
        String nomeUsuarioKeycloak = jwt.getClaim("name");

        // 2. Validar se o endereço pertence ao usuário autenticado
        Endereco enderecoBanco = enderecoRepository.findByIdOptional(dto.idEnderecoEntrega())
                .orElseThrow(() -> new NotFoundException("Endereço não encontrado"));

        System.out.println("Email do dono do endereço: " + enderecoBanco.getPessoa().getEmail());

        if (!enderecoBanco.getPessoa().getEmail().equals(emailUsuarioKeycloak)) {
            throw new ForbiddenException("Email não pertence ao usuário autenticado");
        }

        // 3. Mapear Pedido
        Pedido pedido = new Pedido();
        pedido.setIdUsuario(idUsuarioKeycloak);
        pedido.setNomeClienteSnapshot(nomeUsuarioKeycloak != null ? nomeUsuarioKeycloak : "Cliente Keycloak");
        pedido.setData(LocalDateTime.now());

        // 4. Mapear Endereço (Snapshot)
        // CORREÇÃO: Usar EnderecoEntrega (o Embeddable) e não Endereco (a Entidade)
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

       
        for (ItemPedidoRequest itemDto : dto.itensPedido()) {
            
            // CORREÇÃO: itemDto.idProduto() -> nome correto do campo no Request
            Fonte fonte = fonteRepository.findByIdOptional(itemDto.fonteId())
                    .orElseThrow(() -> new RuntimeException("Fonte não encontrada: " + itemDto.fonteId()));

            // Verificar estoque
            if (fonte.getEstoque() < itemDto.quantidade()) {
                throw new RuntimeException("Estoque insuficiente para a fonte: " + fonte.getNome());
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

        pedido.setTotal(total);      
        pedido.setItens(itensParaSalvar);

        pedidoRepository.persist(pedido);
        
        return PedidoResponse.fromEntity(pedido);
    }

    @Override
    @Transactional
    public List<PedidoResponse> findAll() {
        List<Pedido> pedidos = pedidoRepository.listAll();
        return pedidos.stream()
                .map(PedidoResponse::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public PedidoResponse findById(Long id) {
        Pedido pedido = pedidoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado com id: " + id));
        return PedidoResponse.fromEntity(pedido);
    }

    @Override
    @Transactional
    public PedidoResponse delete(Long id) {
        Pedido pedido = pedidoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado com id: " + id));
        
                PedidoResponse resposta = PedidoResponse.fromEntity(pedido);
                pedidoRepository.delete(pedido);
                return resposta;
    }

    @Override
    @Transactional
    public PedidoResponse update(Long id, PedidoRequest dto) {
        Pedido pedido = pedidoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado com id: " + id));
        
        pedido.setNomeClienteSnapshot(dto.nomeCliente());
        pedido.setTotal(dto.total());
        // Atualizar outros campos conforme necessário

        return PedidoResponse.fromEntity(pedido);
    }

}
