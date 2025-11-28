package gabriel.fontes.br.quarkus.Service;

import gabriel.fontes.br.quarkus.Dto.ModeloRequest;
import gabriel.fontes.br.quarkus.Dto.ModeloResponse;
import gabriel.fontes.br.quarkus.Model.Marca;
import gabriel.fontes.br.quarkus.Model.Modelo;
import gabriel.fontes.br.quarkus.Repository.MarcaRepository;
import gabriel.fontes.br.quarkus.Repository.ModeloRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ModeloServiceImpl implements ModeloService {

    @Inject
    ModeloRepository repository;

    @Inject
    MarcaRepository marcaRepository;

    public List<ModeloResponse> buscarMarcasPorNome(String termoDeBusca) {
        List<Modelo> marcasEncontradas = repository.findByNomeContendo(termoDeBusca);
    
        return marcasEncontradas.stream()
               .map(ModeloResponse::fromEntity)
               .collect(Collectors.toList());
    }
    

    @Override
    @Transactional
    public ModeloResponse create(ModeloRequest dto) {
        Modelo novoModelo = new Modelo();
        novoModelo.setNumeracao(dto.numeracao());

        Marca marca = marcaRepository.findByIdOptional(dto.idMarca())
                .orElseThrow(() -> new NotFoundException("Marca com id " + dto.idMarca() + " não encontrada."));
        novoModelo.setMarca(marca);

        repository.persist(novoModelo);
        return ModeloResponse.fromEntity(novoModelo);
    }

    @Override
    @Transactional
    public ModeloResponse update(Long id, ModeloRequest dto) {
        Modelo modelo = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Modelo com id " + id + " não encontrada."));
        
        modelo.setNumeracao(dto.numeracao());

        Marca marca = marcaRepository.findByIdOptional(dto.idMarca())
                .orElseThrow(() -> new NotFoundException("Marca com id " + dto.idMarca() + " não encontrada."));
        modelo.setMarca(marca);

        return ModeloResponse.fromEntity(modelo);
    }

    @Override
    @Transactional
    public ModeloResponse delete(Long id) {
        Modelo modeloexistente = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Modelo com ID " + id + " não encontrada para exclusão."));

            ModeloResponse resposta = ModeloResponse.fromEntity(modeloexistente);
            repository.delete(modeloexistente);
            return resposta;
        }

    @Override
    public List<ModeloResponse> findAll() {
        return repository.listAll().stream()
                .map(ModeloResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ModeloResponse findById(Long id) {
        Modelo modelo = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Modelo com id " + id + " não encontrada."));
        return ModeloResponse.fromEntity(modelo);
    }
}