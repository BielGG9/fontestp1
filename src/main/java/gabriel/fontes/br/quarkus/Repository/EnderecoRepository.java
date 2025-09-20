package gabriel.fontes.br.quarkus.Repository;

import gabriel.fontes.br.quarkus.Model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {
    

}
