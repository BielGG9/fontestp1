package gabriel.fontes.br.quarkus.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gabriel.fontes.br.quarkus.Dto.ClienteRequest;
import gabriel.fontes.br.quarkus.Dto.ClienteResponse;
import gabriel.fontes.br.quarkus.Dto.EnderecoRequest;
import gabriel.fontes.br.quarkus.Dto.TelefoneRequest;
import gabriel.fontes.br.quarkus.Model.Cliente;
import gabriel.fontes.br.quarkus.Model.Endereco;
import gabriel.fontes.br.quarkus.Model.Telefone;
import gabriel.fontes.br.quarkus.Repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {
    
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


        if (dto.telefones() != null && !dto.telefones().isEmpty()) {
            List<Telefone> telefones = new ArrayList<>();
            for (TelefoneRequest telDto : dto.telefones()) {
                Telefone telefone = new Telefone();
                telefone.setDdd(telDto.ddd());
                telefone.setNumero(telDto.numero());
                telefone.setCliente(novoCliente);  
                telefones.add(telefone);
            }
            novoCliente.setTelefones(telefones);
        }

        if (dto.enderecos() != null && !dto.enderecos().isEmpty()) {
            List<Endereco> enderecos = new ArrayList<>();
            for (EnderecoRequest endDto : dto.enderecos()) {
                Endereco endereco = new Endereco();
                endereco.setRua(endDto.rua());
                endereco.setNumero(endDto.numero());
                endereco.setComplemento(endDto.complemento());
                endereco.setBairro(endDto.bairro());
                endereco.setCidade(endDto.cidade());
                endereco.setEstado(endDto.estado());
                endereco.setCep(endDto.cep());
                endereco.setCliente(novoCliente); 
                enderecos.add(endereco);
            }
            novoCliente.setEnderecos(enderecos);
        }

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

        cliente.getTelefones().clear();
        if (dto.telefones() != null) {
            for (TelefoneRequest telDto : dto.telefones()) {
                Telefone telefone = new Telefone();
                telefone.setDdd(telDto.ddd());
                telefone.setNumero(telDto.numero());
                telefone.setCliente(cliente);
                cliente.getTelefones().add(telefone);
            }
        }
        
        cliente.getEnderecos().clear(); 
        if (dto.enderecos() != null) {
             for (EnderecoRequest endDto : dto.enderecos()) {
                Endereco endereco = new Endereco();
                endereco.setRua(endDto.rua());
                // ... etc ...
                endereco.setCliente(cliente);
                cliente.getEnderecos().add(endereco);
            }
        }

        return ClienteResponse.fromEntity(cliente);
    }

    
    @Override
        @Transactional 
        public ClienteResponse delete(Long id) {
            Cliente clienteExistente = repository.findByIdOptional(id)
                
                .orElseThrow(() -> new NotFoundException("Cliente com id " + id + " nao encontrado para exclusão."));

            ClienteResponse resposta = ClienteResponse.fromEntity(clienteExistente);
            repository.delete(clienteExistente);
            return resposta;
        }

    @Override
    public List<ClienteResponse> findAll() {
        return repository
        .listAll()
        .stream()
        .map(ClienteResponse::fromEntity)
        .collect(Collectors.toList());
    }

    @Override
    public ClienteResponse findById(Long id) {
        return repository
        .findByIdOptional(id)
        .map(ClienteResponse::fromEntity)
        .orElseThrow(() -> new NotFoundException("Cliente com id " + id + " não encontrado."));
    }
}