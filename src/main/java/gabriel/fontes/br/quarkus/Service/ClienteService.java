package gabriel.fontes.br.quarkus.Service;

import java.util.List;

import gabriel.fontes.br.quarkus.Dto.ClienteRequest;
import gabriel.fontes.br.quarkus.Dto.ClienteResponse;

public interface ClienteService {
    
    ClienteResponse create(ClienteRequest dto);
    ClienteResponse update(Long id, ClienteRequest dto);
    ClienteResponse delete(Long id);
    List<ClienteResponse> findAll();
    ClienteResponse findById(Long id);
    
}
