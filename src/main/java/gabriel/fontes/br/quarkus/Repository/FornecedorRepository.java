package gabriel.fontes.br.quarkus.Repository;

import java.util.List;

import gabriel.fontes.br.quarkus.Model.Fornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor> {

    public List<Fornecedor> findByNomeContendo(String termoDeBusca) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNomeContendo'");
    }
    
}
