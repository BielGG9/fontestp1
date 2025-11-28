package gabriel.fontes.br.quarkus.Service;

import gabriel.fontes.br.quarkus.Dto.FonteRequest;
import gabriel.fontes.br.quarkus.Dto.FonteResponse;
import gabriel.fontes.br.quarkus.Model.Fonte;
import gabriel.fontes.br.quarkus.Model.Modelo;
import gabriel.fontes.br.quarkus.Model.Enums.Certificacao;
import gabriel.fontes.br.quarkus.Repository.FonteRepository;
import gabriel.fontes.br.quarkus.Repository.ModeloRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped 
public class FonteServiceImpl implements FonteService {

    @Inject 
    FonteRepository repository;

    @Inject 
    ModeloRepository modeloRepository;

    @Override
    @Transactional 
    public FonteResponse create(FonteRequest dto) {
     
        Fonte novaFonte = new Fonte();

        novaFonte.setNome(dto.nome());
        novaFonte.setPotencia(dto.potencia());
        novaFonte.setPreco(dto.preco());
        novaFonte.setEstoque(dto.estoque());
        
        Modelo modelo = modeloRepository.findByIdOptional(dto.idModelo())
                .orElseThrow(() -> new NotFoundException("Modelo com id " + dto.idModelo() + " não encontrado."));
        novaFonte.setModelo(modelo);
        
        novaFonte.setCertificacao(Certificacao.valueOf(dto.certificacao().toUpperCase()));

        repository.persist(novaFonte);

        return FonteResponse.fromEntity(novaFonte);
    }

    
    @Override
    public List<FonteResponse> findAll() {
        
        return repository.listAll().stream()
                .map(FonteResponse::fromEntity)
                .collect(Collectors.toList());
    }

    
    @Override
    public FonteResponse findById(Long id) {
        
        Fonte fonte = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Fonte com ID " + id + " não encontrada."));
        
        return FonteResponse.fromEntity(fonte);
    }

   
    @Override
    @Transactional
    public FonteResponse update(Long id, FonteRequest dto) {
        
        Fonte fonteExistente = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Fonte com ID " + id + " não encontrada para atualização."));

       
        fonteExistente.setNome(dto.nome());
        fonteExistente.setPotencia(dto.potencia());
        fonteExistente.setPreco(dto.preco());
        fonteExistente.setEstoque(dto.estoque());

        Modelo modelo = modeloRepository.findByIdOptional(dto.idModelo())
                .orElseThrow(() -> new NotFoundException("Modelo com id " + dto.idModelo() + " não encontrado."));
        fonteExistente.setModelo(modelo);

        fonteExistente.setCertificacao(Certificacao.valueOf(dto.certificacao().toUpperCase()));


        return FonteResponse.fromEntity(fonteExistente);
    }

    @Override
    @Transactional
    public FonteResponse delete(Long id) {
        Fonte fonteExistente = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Fonte com ID " + id + " não encontrada para exclusão."));

            FonteResponse resposta = FonteResponse.fromEntity(fonteExistente);
            repository.delete(fonteExistente);
            return resposta;
        }
    }
