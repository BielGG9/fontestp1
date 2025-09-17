package gabriel.fontes.br.quarkus.Service;

import gabriel.fontes.br.quarkus.Dto.MarcaRequest;
import gabriel.fontes.br.quarkus.Dto.MarcaResponse;
import java.util.List;

public interface MarcaService {

    MarcaResponse create(MarcaRequest dto);

    MarcaResponse update(Long id, MarcaRequest dto);

    void delete(Long id);

    List<MarcaResponse> findAll();

    MarcaResponse findById(Long id);
}