package gabriel.fontes.br.quarkus.Service;

import java.util.List;


import gabriel.fontes.br.quarkus.Dto.UsuarioRequest;
import gabriel.fontes.br.quarkus.Dto.UsuarioResponse;

public interface UsuarioService {

    UsuarioResponse findById(Long id);
    List<UsuarioResponse> findAll();
    UsuarioResponse create(UsuarioRequest dto);
}
