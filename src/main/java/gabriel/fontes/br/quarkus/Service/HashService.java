package gabriel.fontes.br.quarkus.Service;

public interface HashService {

    String getHashSenha(String senha);

    boolean verificarSenha(String senha, String hash);
}
