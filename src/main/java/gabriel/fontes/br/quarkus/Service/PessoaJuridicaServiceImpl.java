package gabriel.fontes.br.quarkus.Service;

import gabriel.fontes.br.quarkus.Dto.PessoaJuridicaRequest;
import gabriel.fontes.br.quarkus.Dto.PessoaJuridicaResponse;
import gabriel.fontes.br.quarkus.Model.Jpa.PessoaJuridica;
import gabriel.fontes.br.quarkus.Repository.PessoaJuridicaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService{

    @Inject
    PessoaJuridicaRepository repository;

    public List<PessoaJuridicaResponse> buscarPessoasPorNome(String termoDeBusca) {
    List<PessoaJuridica> pessoasEncontradas = repository.findByNomeContendo(termoDeBusca);

    return pessoasEncontradas.stream()
               .map(PessoaJuridicaResponse::fromEntity)
               .collect(Collectors.toList());
}
    
    @Override
    @Transactional
    public PessoaJuridicaResponse create(PessoaJuridicaRequest dto) {
        PessoaJuridica novaPessoa = new PessoaJuridica();
        novaPessoa.setCnpj(dto.cnpj());
        novaPessoa.setInscricaoEstadual(dto.inscricaoEstadual());
        repository.persist(novaPessoa);
        return PessoaJuridicaResponse.fromEntity(novaPessoa);
    }

    @Override
    @Transactional
    public PessoaJuridicaResponse update(Long id, PessoaJuridicaRequest dto) {
        PessoaJuridica pessoa = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pessoa Jurídica com id " + id + " não encontrada."));
        pessoa.setCnpj(dto.cnpj());
        pessoa.setInscricaoEstadual(dto.inscricaoEstadual());
        return PessoaJuridicaResponse.fromEntity(pessoa);
    }

    @Override
    @Transactional
    public PessoaJuridicaResponse delete(Long id) {
        PessoaJuridica pessoaExistente = repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Pessoa Jurídica com ID " + id + " não encontrada para exclusão."));

            PessoaJuridicaResponse resposta = PessoaJuridicaResponse.fromEntity(pessoaExistente);
            repository.delete(pessoaExistente);
            return resposta;
        }

    @Override
    public List<PessoaJuridicaResponse> findAll() {
        return repository.listAll().stream()
                .map(PessoaJuridicaResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public PessoaJuridicaResponse findById(Long id) {
        PessoaJuridica pessoa = repository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Pessoa Jurídica com id " + id + " não encontrada."));
        return PessoaJuridicaResponse.fromEntity(pessoa);
    }
}