package gabriel.fontes.br.quarkus.Repository;

import java.util.Optional;

import gabriel.fontes.br.quarkus.Model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Optional<Usuario> findBylogin(String login) {
        return find("login", login).firstResultOptional();
    }
}
