package gabriel.fontes.br.quarkus.Service;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotFoundException;

import gabriel.fontes.br.quarkus.Dto.EnderecoRequest;
import gabriel.fontes.br.quarkus.Dto.EnderecoResponse;
import gabriel.fontes.br.quarkus.Model.Cliente;
import gabriel.fontes.br.quarkus.Model.Endereco;
import gabriel.fontes.br.quarkus.Repository.ClienteRepository;
import gabriel.fontes.br.quarkus.Repository.EnderecoRepository;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Override
    @Transactional
    public EnderecoResponse create(Long clienteId, EnderecoRequest dto) {
        Cliente cliente = clienteRepository.findByIdOptional(clienteId)
            .orElseThrow(() -> new NotFoundException("Cliente com ID " + clienteId + " não encontrado."));

        Endereco novoEndereco = new Endereco();
        mapearDtoParaEntidade(dto, novoEndereco);
        
        novoEndereco.setCliente(cliente);
        enderecoRepository.persist(novoEndereco);

        return EnderecoResponse.fromEntity(novoEndereco);
    }

    @Override
    @Transactional
    public EnderecoResponse update(Long clienteId, Long enderecoId, EnderecoRequest dto) {
        clienteRepository.findByIdOptional(clienteId)
            .orElseThrow(() -> new NotFoundException("Cliente com ID " + clienteId + " não encontrado."));
        
        Endereco endereco = enderecoRepository.findByIdOptional(enderecoId)
            .orElseThrow(() -> new NotFoundException("Endereço com ID " + enderecoId + " não encontrado."));

        if (!endereco.getCliente().getId().equals(clienteId)) {
            throw new ForbiddenException("Este endereço não pertence ao cliente informado.");
        }

        mapearDtoParaEntidade(dto, endereco);
        return EnderecoResponse.fromEntity(endereco);
    }

@Override
@Transactional
public EnderecoResponse delete(Long clienteId, Long enderecoId) {
    clienteRepository.findByIdOptional(clienteId)
        .orElseThrow(() -> new NotFoundException("Cliente com ID " + clienteId + " não encontrado."));

    Endereco endereco = enderecoRepository.findByIdOptional(enderecoId)
        .orElseThrow(() -> new NotFoundException("Endereço com ID " + enderecoId + " não encontrado."));

    if (!endereco.getCliente().getId().equals(clienteId)) {
        throw new ForbiddenException("Este endereço não pertence ao cliente informado.");
    }

    EnderecoResponse resposta = EnderecoResponse.fromEntity(endereco);
    enderecoRepository.delete(endereco);
    return resposta;
}


    @Override
    public EnderecoResponse findById(Long clienteId, Long enderecoId) {
        Endereco endereco = enderecoRepository.findByIdOptional(enderecoId)
            .orElseThrow(() -> new NotFoundException("Endereço com ID " + enderecoId + " não encontrado."));
        if (!endereco.getCliente().getId().equals(clienteId)) {
            throw new ForbiddenException("Este endereço não pertence ao cliente informado.");
        }
        return EnderecoResponse.fromEntity(endereco);
    }

    @Override
    public List<EnderecoResponse> findByClienteId(Long clienteId) {
        Cliente cliente = clienteRepository.findByIdOptional(clienteId)
            .orElseThrow(() -> new NotFoundException("Cliente com ID " + clienteId + " não encontrado."));
        
        return cliente.getEnderecos().stream()
            .map(EnderecoResponse::fromEntity)
            .toList();
    }
    
    private void mapearDtoParaEntidade(EnderecoRequest dto, Endereco entidade) {
        entidade.setRua(dto.rua());
        entidade.setBairro(dto.bairro());
        entidade.setNumero(dto.numero());
        entidade.setComplemento(dto.complemento());
        entidade.setCidade(dto.cidade());
        entidade.setEstado(dto.estado());
        entidade.setCep(dto.cep());
    }
}
