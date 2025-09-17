package gabriel.fontes.br.quarkus.Service;

import gabriel.fontes.br.quarkus.Dto.MarcaRequest;
import gabriel.fontes.br.quarkus.Dto.MarcaResponse;
import gabriel.fontes.br.quarkus.Model.Marca;
import gabriel.fontes.br.quarkus.Repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MarcaServiceImpl implements MarcaService {

    @Inject
    MarcaRepository repository;

    @Override
    @Transactional
    public MarcaResponse create(MarcaRequest dto) {
        Marca novaMarca = new Marca();
        novaMarca.setNome(dto.nome());
        repository.persist(novaMarca);
        return MarcaResponse.fromEntity(novaMarca);
    }

    @Override
    @Transactional
    public MarcaResponse update(Long id, MarcaRequest dto) {
        Marca marca = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Marca com id " + id + " não encontrada."));
        marca.setNome(dto.nome());
        return MarcaResponse.fromEntity(marca);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Marca com id " + id + " não encontrada para exclusão.");
        }
    }

    @Override
    public List<MarcaResponse> findAll() {
        return repository.listAll().stream()
                .map(MarcaResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MarcaResponse findById(Long id) {
        Marca marca = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Marca com id " + id + " não encontrada."));
        return MarcaResponse.fromEntity(marca);
    }
}