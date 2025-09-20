package gabriel.fontes.br.quarkus.Repository;

import gabriel.fontes.br.quarkus.Model.Telefone;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelefoneRepository implements PanacheRepository<Telefone> {
    
}
