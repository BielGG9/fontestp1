package gabriel.fontes.br.quarkus.Service;

import gabriel.fontes.br.quarkus.Dto.ClienteRequest;
import gabriel.fontes.br.quarkus.Dto.ClienteResponse;
import gabriel.fontes.br.quarkus.Model.Cliente;
import gabriel.fontes.br.quarkus.Repository.ClienteRepository;
import io.quarkus.security.ForbiddenException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService{

    @Inject
    ClienteRepository repository;

    @Inject
    JsonWebToken jwt;

    public List<ClienteResponse> buscarClientesPorNome(String termoDeBusca) {
    // Usar o repositório para buscar clientes cujo nome contenha o termo de busca (ignorando maiúsculas/minúsculas)
    List<Cliente> clientesEncontrados = repository.findByNomeContendo(termoDeBusca);

    // Converter a lista de entidades Cliente para uma lista de DTOs ClienteResponse
    return clientesEncontrados.stream()
               .map(ClienteResponse::fromEntity)
               .collect(Collectors.toList());
}
    @Override
    public ClienteResponse getMeuPerfil() {
        // Extrair o ID do usuário autenticado do token JWT
        String idUsuarioKeycloak = jwt.getSubject();

        // Verificar se o ID do usuário foi obtido corretamente
        if (idUsuarioKeycloak == null) {
            throw new NotAuthorizedException("Usuário não autenticado.");
        }
        
        // Buscar o cliente associado ao ID do usuário Keycloak
        Cliente cliente =  repository.findByIdKeycloak(idUsuarioKeycloak);

        // Verificar se o cliente foi encontrado
        if (cliente == null) {
            throw new NotFoundException("Cliente não encontrado para o usuário autenticado.");
        }

        return ClienteResponse.fromEntity(cliente);
    }

    public void imprimirDadosToken() {
       // Extrair informações do token JWT  
       String idUsuarioKeycloak = jwt.getSubject();
       String nomeUsuario = jwt.getName();
       String emailUsuario = jwt.getClaim("email");

       System.out.println("ID do Usuário Keycloak: " + idUsuarioKeycloak);
       System.out.println("Nome do Usuário: " + nomeUsuario);
       System.out.println("Email do Usuário: " + emailUsuario);
    }

    @Override
    @Transactional
    public ClienteResponse create(ClienteRequest dto) {
        // Imprimir dados do token para depuração
        String idUsuarioKeycloak = jwt.getSubject();

        // Verificar se o usuário já está cadastrado como cliente
        if (repository.findByIdKeycloak(idUsuarioKeycloak) != null) {
            throw new ForbiddenException("Usuário já cadastrado como cliente.");
        }

        // Criar um novo cliente com os dados fornecidos
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(dto.nome());
        novoCliente.setEmail(dto.email());
        novoCliente.setCpf(dto.cpf());
        novoCliente.setRg(dto.rg());
        novoCliente.setDataCadastro(dto.dataCadastro());
        novoCliente.setIdKeycloak(idUsuarioKeycloak);

        repository.persist(novoCliente);
        return ClienteResponse.fromEntity(novoCliente);
    }

    @Override
    @Transactional
    public ClienteResponse update(Long id, ClienteRequest dto) {
        // Buscar o cliente existente pelo ID
        Cliente cliente = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cliente com id " + id + " não encontrado."));
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
            cliente.setCpf(dto.cpf());
            cliente.setRg(dto.rg());
            cliente.setDataCadastro(dto.dataCadastro());
        return ClienteResponse.fromEntity(cliente);
    }

    @Override
    @Transactional
    public ClienteResponse delete(Long id) {
        // Verificar se o cliente existe antes de deletar
        Cliente clienteExistente = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Cliente com ID " + id + " não encontrado para exclusão."));

        ClienteResponse resposta = ClienteResponse.fromEntity(clienteExistente);
        repository.delete(clienteExistente);
        return resposta;
    }

    @Override
    public List<ClienteResponse> findAll() {
        // Converter a lista de entidades Cliente para uma lista de DTOs ClienteResponse
        return repository.listAll().stream()
                .map(ClienteResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponse findById(Long id) {
        // Buscar o cliente pelo ID e lançar exceção se não encontrado
        Cliente cliente = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cliente com id " + id + " não encontrado."));
        return ClienteResponse.fromEntity(cliente);
    }
}