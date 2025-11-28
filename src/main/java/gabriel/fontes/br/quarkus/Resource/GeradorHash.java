package gabriel.fontes.br.quarkus.Resource;


import io.quarkus.elytron.security.common.BcryptUtil;

public class GeradorHash {
    
    public static void main(String[] args) {
        String senha = "admin123";
        String hash = BcryptUtil.bcryptHash(senha);
        System.out.println("Hash Gerado!! " + hash);
    }
}
