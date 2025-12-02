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
    List<Cliente> clientesEncontrados = repository.findByNomeContendo(termoDeBusca);

    return clientesEncontrados.stream()
               .map(ClienteResponse::fromEntity)
               .collect(Collectors.toList());
}
    @Override
    public ClienteResponse getMeuPerfil() {
        String idUsuarioKeycloak = jwt.getSubject();

        if (idUsuarioKeycloak == null) {
            throw new NotAuthorizedException("Usuário não autenticado.");
        }
        
        Cliente cliente =  repository.findByIdKeycloak(idUsuarioKeycloak);

        if (cliente == null) {
            throw new NotFoundException("Cliente não encontrado para o usuário autenticado.");
        }

        return ClienteResponse.fromEntity(cliente);
    }

    public void imprimirDadosToken() {
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

        String idUsuarioKeycloak = jwt.getSubject();

        if (repository.findByIdKeycloak(idUsuarioKeycloak) != null) {
            throw new ForbiddenException("Usuário já cadastrado como cliente.");
        }

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
        Cliente clienteExistente = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Cliente com ID " + id + " não encontrado para exclusão."));

        ClienteResponse resposta = ClienteResponse.fromEntity(clienteExistente);
        repository.delete(clienteExistente);
        return resposta;
    }

    @Override
    public List<ClienteResponse> findAll() {
        return repository.listAll().stream()
                .map(ClienteResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponse findById(Long id) {
        Cliente cliente = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cliente com id " + id + " não encontrado."));
        return ClienteResponse.fromEntity(cliente);
    }
}