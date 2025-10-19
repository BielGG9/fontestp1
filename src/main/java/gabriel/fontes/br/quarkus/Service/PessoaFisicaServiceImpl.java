package gabriel.fontes.br.quarkus.Service;


import gabriel.fontes.br.quarkus.Dto.PessoaFisicaRequest;
import gabriel.fontes.br.quarkus.Dto.PessoaFisicaResponse;
import gabriel.fontes.br.quarkus.Model.PessoaFisica;
import gabriel.fontes.br.quarkus.Repository.PessoaFisicaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PessoaFisicaServiceImpl implements PessoaFisicaService{

    @Inject
    PessoaFisicaRepository repository;

    public List<PessoaFisicaResponse> buscarPessoasPorNome(String termoDeBusca) {
    List<PessoaFisica> pessoasEncontradas = repository.findByNomeContendo(termoDeBusca);

    return pessoasEncontradas.stream()
               .map(PessoaFisicaResponse::fromEntity)
               .collect(Collectors.toList());
}
    
    @Override
    @Transactional
    public PessoaFisicaResponse create(PessoaFisicaRequest dto) {
        PessoaFisica novaPessoa = new PessoaFisica();
        novaPessoa.setCpf(dto.cpf());
        novaPessoa.setRg(dto.rg());
        repository.persist(novaPessoa);
        return PessoaFisicaResponse.fromEntity(novaPessoa);
    }

    @Override
    @Transactional
    public PessoaFisicaResponse update(Long id, PessoaFisicaRequest dto) {
        PessoaFisica pessoa = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pessoa Física com id " + id + " não encontrada."));
        pessoa.setCpf(dto.cpf());
        pessoa.setRg(dto.rg());
        return PessoaFisicaResponse.fromEntity(pessoa);
    }

    @Override
    @Transactional
    public PessoaFisicaResponse delete(Long id) {
        PessoaFisica pessoaExistente = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Pessoa Física com ID " + id + " não encontrada para exclusão."));

            PessoaFisicaResponse resposta = PessoaFisicaResponse.fromEntity(pessoaExistente);
            repository.delete(pessoaExistente);
            return resposta;
        }

    @Override
    public List<PessoaFisicaResponse> findAll() {
        return repository.listAll().stream()
                .map(PessoaFisicaResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PessoaFisicaResponse findById(Long id) {
        PessoaFisica pessoa = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pessoa Física com id " + id + " não encontrada."));
        return PessoaFisicaResponse.fromEntity(pessoa);
    }
}