// gabriel/fontes/br/quarkus/Service/EnderecoService.java
package gabriel.fontes.br.quarkus.Service;

import java.util.List;
import gabriel.fontes.br.quarkus.Dto.EnderecoRequest;
import gabriel.fontes.br.quarkus.Dto.EnderecoResponse;

public interface EnderecoService {
    
    EnderecoResponse create(Long clienteId, EnderecoRequest dto);

    EnderecoResponse update(Long clienteId, Long enderecoId, EnderecoRequest dto);
    EnderecoResponse delete(Long clienteId, Long enderecoId);
    EnderecoResponse findById(Long clienteId, Long enderecoId);
    List<EnderecoResponse> findByClienteId(Long clienteId);
}