package gabriel.fontes.br.quarkus.Service;

import java.util.List;

import gabriel.fontes.br.quarkus.Dto.PessoaJuridicaRequest;
import gabriel.fontes.br.quarkus.Dto.PessoaJuridicaResponse;


public interface PessoaJuridicaService {

    PessoaJuridicaResponse findById(Long id);
    List<PessoaJuridicaResponse> findAll();
    PessoaJuridicaResponse delete(Long id);
    PessoaJuridicaResponse update(Long id, PessoaJuridicaRequest dto);
    PessoaJuridicaResponse create(PessoaJuridicaRequest dto);

}
