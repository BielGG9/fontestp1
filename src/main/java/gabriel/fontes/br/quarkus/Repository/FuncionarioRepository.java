package gabriel.fontes.br.quarkus.Repository;

import java.util.List;

import gabriel.fontes.br.quarkus.Model.Funcionario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {

    public List<Funcionario> findByNomeContendo(String termoDeBusca) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNomeContendo'");
    }
    
}
