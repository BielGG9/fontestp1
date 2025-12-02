package gabriel.fontes.br.quarkus.Service;

import java.util.List;

import gabriel.fontes.br.quarkus.Dto.ClienteRequest;
import gabriel.fontes.br.quarkus.Dto.ClienteResponse;
import gabriel.fontes.br.quarkus.Model.Cliente;

public interface ClienteService {
    
    ClienteResponse create(ClienteRequest dto);
    ClienteResponse update(Long id, ClienteRequest dto);
    ClienteResponse delete(Long id);
    List<ClienteResponse> findAll();
    ClienteResponse findById(Long id);
    ClienteResponse getMeuPerfil();
    
    
}
