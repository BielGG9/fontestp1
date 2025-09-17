package gabriel.fontes.br.quarkus.Repository;

import gabriel.fontes.br.quarkus.Model.Marca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {
    
    
}
