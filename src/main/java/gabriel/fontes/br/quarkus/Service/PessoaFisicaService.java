package gabriel.fontes.br.quarkus.Service;

import java.util.List;

import gabriel.fontes.br.quarkus.Dto.PessoaFisicaRequest;
import gabriel.fontes.br.quarkus.Dto.PessoaFisicaResponse;

public interface PessoaFisicaService {
    
    PessoaFisicaResponse findById(Long id);
    List<PessoaFisicaResponse> findAll();
    PessoaFisicaResponse delete(Long id);
    PessoaFisicaResponse update(Long id, PessoaFisicaRequest dto);
    PessoaFisicaResponse create(PessoaFisicaRequest dto);
}
