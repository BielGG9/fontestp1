package gabriel.fontes.br.quarkus.Repository;

import java.util.List;
import java.util.Optional; 

import gabriel.fontes.br.quarkus.Model.PessoaFisica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisica> {

    
    public Optional<PessoaFisica> findByNomeExato(String nome) {
        return find("nome", nome).firstResultOptional();
    }

    public List<PessoaFisica> findByNomeContendo(String texto) {
        String parametroDeBusca = "%" + texto.toUpperCase() + "%"; 
                return find("upper(nome) LIKE ?1", parametroDeBusca).list();
    }
}