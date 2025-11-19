package gabriel.fontes.br.quarkus.Service;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import gabriel.fontes.br.quarkus.Model.Usuario;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenServiceImpl implements TokenService {

    @Override
    public String gerarToken(Usuario usuario) {
        Set<String> roles = new HashSet<>();
        
        roles.add(usuario.getPerfil().name()); 

        return Jwt.issuer("https://fontes.br/api")
                  .subject(usuario.getLogin()) 
                  .groups(roles)
                  .expiresIn(Duration.ofHours(8))
                  .sign();
    }
}