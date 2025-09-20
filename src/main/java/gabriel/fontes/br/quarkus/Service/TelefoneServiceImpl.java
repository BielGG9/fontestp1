package gabriel.fontes.br.quarkus.Service;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotFoundException;
import gabriel.fontes.br.quarkus.Dto.ClienteResponse;
import gabriel.fontes.br.quarkus.Dto.TelefoneRequest;
import gabriel.fontes.br.quarkus.Dto.TelefoneResponse;
import gabriel.fontes.br.quarkus.Model.Cliente;
import gabriel.fontes.br.quarkus.Model.Telefone;
import gabriel.fontes.br.quarkus.Repository.ClienteRepository;
import gabriel.fontes.br.quarkus.Repository.TelefoneRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class TelefoneServiceImpl implements TelefoneService {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    TelefoneRepository telefoneRepository;

    @Override
    @Transactional
    public TelefoneResponse create(Long clienteId, TelefoneRequest dto) {
        Cliente cliente = clienteRepository.findByIdOptional(clienteId)
            .orElseThrow(() -> new NotFoundException("Cliente com ID " + clienteId + " não encontrado."));

        Telefone novoTelefone = new Telefone();
        mapearDtoParaEntidade(dto, novoTelefone);
        
        novoTelefone.setCliente(cliente);
        telefoneRepository.persist(novoTelefone);

        return TelefoneResponse.fromEntity(novoTelefone);
    }

@Override
@Transactional
public TelefoneResponse delete(Long clienteId, Long telefoneId) {
    clienteRepository.findByIdOptional(clienteId)
        .orElseThrow(() -> new NotFoundException("Cliente com ID " + clienteId + " não encontrado."));

    Telefone telefone = telefoneRepository.findByIdOptional(telefoneId)
        .orElseThrow(() -> new NotFoundException("Telefone com ID " + telefoneId + " não encontrado."));

    if (!telefone.getCliente().getId().equals(clienteId)) {
        throw new ForbiddenException("Este telefone não pertence ao cliente informado.");
    }

    TelefoneResponse resposta = TelefoneResponse.fromEntity(telefone);
    telefoneRepository.delete(telefone);
    return resposta;
}
    

    @Override
    public TelefoneResponse findById(Long clienteId, Long telefoneId) {
        Telefone telefone = telefoneRepository.findByIdOptional(telefoneId)
            .orElseThrow(() -> new NotFoundException("Telefone com ID " + telefoneId + " não encontrado."));
        if (!telefone.getCliente().getId().equals(clienteId)) {
            throw new ForbiddenException("Este telefone não pertence ao cliente informado.");
        }
        return TelefoneResponse.fromEntity(telefone);
    }

    @Override
    public List<TelefoneResponse> findByClienteId(Long clienteId) {
        Cliente cliente = clienteRepository.findByIdOptional(clienteId)
            .orElseThrow(() -> new NotFoundException("Cliente com ID " + clienteId + " não encontrado."));
        
        return cliente.getTelefones().stream()
            .map(TelefoneResponse::fromEntity)
            .toList();
    }
    
    private void mapearDtoParaEntidade(TelefoneRequest dto, Telefone entidade) {
        entidade.setDdd(dto.ddd());
        entidade.setNumero(dto.numero());
    }

    @Override
    public TelefoneResponse update(Long clienteId, Long telefoneId, TelefoneRequest dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}