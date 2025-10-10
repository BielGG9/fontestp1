package gabriel.fontes.br.quarkus.Service;

import gabriel.fontes.br.quarkus.Dto.ClienteRequest;
import gabriel.fontes.br.quarkus.Dto.ClienteResponse;
import gabriel.fontes.br.quarkus.Model.Cliente;
import gabriel.fontes.br.quarkus.Repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService{

    @Inject
    ClienteRepository repository;

    public List<ClienteResponse> buscarClientesPorNome(String termoDeBusca) {
    List<Cliente> clientesEncontrados = repository.findByNomeContendo(termoDeBusca);

    return clientesEncontrados.stream()
               .map(ClienteResponse::fromEntity)
               .collect(Collectors.toList());
}
    
    @Override
    @Transactional
    public ClienteResponse create(ClienteRequest dto) {
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(dto.nome());
        novoCliente.setEmail(dto.email());
        novoCliente.setCpf(dto.cpf());
        novoCliente.setRg(dto.rg());
            novoCliente.setDataCadastro(dto.dataCadastro());
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