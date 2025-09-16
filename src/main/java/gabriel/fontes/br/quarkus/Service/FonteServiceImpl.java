package gabriel.fontes.br.quarkus.Service;

import gabriel.fontes.br.quarkus.Dto.FonteRequest;
import gabriel.fontes.br.quarkus.Dto.FonteResponse;
import gabriel.fontes.br.quarkus.Model.Fonte;
import gabriel.fontes.br.quarkus.Repository.FonteRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped // Define esta classe como um bean gerenciado pelo Quarkus
public class FonteServiceImpl implements FonteService {

    @Inject // Injeta o repositório para interagir com o banco de dados
    FonteRepository repository;

    /**
     * CREATE
     * Cria uma nova Fonte no banco de dados.
     */
    @Override
    @Transactional // Necessário para qualquer operação de escrita (create, update, delete)
    public FonteResponse create(FonteRequest dto) {
        // 1. Cria uma nova entidade vazia
        Fonte novaFonte = new Fonte();

        // 2. Transfere os dados do DTO (record) para a entidade
        novaFonte.setNome(dto.nome());
        novaFonte.setPotencia(dto.potencia());
        novaFonte.setPreco(dto.preco());
        novaFonte.setFabricante(dto.fabricante());

        // 3. Persiste a entidade no banco de dados
        repository.persist(novaFonte);

        // 4. Retorna um DTO de resposta a partir da entidade salva (que agora tem um ID)
        return FonteResponse.fromEntity(novaFonte);
    }

    /**
     * READ (All)
     * Busca todas as Fontes do banco de dados.
     */
    @Override
    public List<FonteResponse> findAll() {
        // Usa Stream para converter a lista de entidades 'Fonte' em uma lista de 'FonteResponse'
        return repository.listAll().stream()
                .map(FonteResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * READ (By ID)
     * Busca uma única Fonte pelo seu ID.
     */
    @Override
    public FonteResponse findById(Long id) {
        // Usa 'findByIdOptional' para buscar a fonte.
        // Se não encontrar, lança uma NotFoundException (que resulta em um erro HTTP 404).
        Fonte fonte = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Fonte com ID " + id + " não encontrada."));
        
        return FonteResponse.fromEntity(fonte);
    }

    /**
     * UPDATE
     * Atualiza uma Fonte existente.
     */
    @Override
    @Transactional
    public FonteResponse update(Long id, FonteRequest dto) {
        // 1. Primeiro, busca a fonte existente no banco. Lança erro 404 se não existir.
        Fonte fonteExistente = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Fonte com ID " + id + " não encontrada para atualização."));

        // 2. Atualiza os campos da entidade com os dados do DTO
        fonteExistente.setNome(dto.nome());
        fonteExistente.setPotencia(dto.potencia());
        fonteExistente.setPreco(dto.preco());
        fonteExistente.setFabricante(dto.fabricante());

        // 3. O Hibernate/Panache gerencia a atualização automaticamente ao final da transação.
        //    Não é necessário chamar 'repository.persist()' novamente.

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