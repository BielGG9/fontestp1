package gabriel.fontes.br.quarkus.Service;

import gabriel.fontes.br.quarkus.Model.Usuario;

public interface TokenService {

    String gerarToken (Usuario usuario);

}
