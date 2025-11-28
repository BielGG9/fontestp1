package gabriel.fontes.br.quarkus.Service;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HashServiceImpl implements HashService {

    @Override
    public String getHashSenha(String senha) {
        return BcryptUtil.bcryptHash(senha);
    }

    @Override
    public boolean verificarSenha(String senha, String hash) {
        return BcryptUtil.matches(senha, hash);
    }
}