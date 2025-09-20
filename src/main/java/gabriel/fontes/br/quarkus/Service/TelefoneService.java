package gabriel.fontes.br.quarkus.Service;

import java.util.List;
import gabriel.fontes.br.quarkus.Dto.TelefoneRequest;
import gabriel.fontes.br.quarkus.Dto.TelefoneResponse;

public interface TelefoneService {

    TelefoneResponse create(Long clienteId, TelefoneRequest dto);
    TelefoneResponse update(Long clienteId, Long telefoneId, TelefoneRequest dto);
    TelefoneResponse delete(Long clienteId, Long telefoneId);
    TelefoneResponse findById(Long clienteId, Long telefoneId);
    List<TelefoneResponse> findByClienteId(Long clienteId);
}