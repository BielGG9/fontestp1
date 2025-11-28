package gabriel.fontes.br.quarkus.Repository;

import java.util.List;
import java.util.Optional;

import gabriel.fontes.br.quarkus.Model.Telefone;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelefoneRepository implements PanacheRepository<Telefone> {
    
     
    public Optional<Telefone> findByNomeExato(String nome) {
        return find("nome", nome).firstResultOptional();
    }

    public List<Telefone> findByNomeContendo(String texto) {
        String parametroDeBusca = "%" + texto.toUpperCase() + "%"; // Adicionamos os '%' e convertemos para maiúsculo
                return find("upper(nome) LIKE ?1", parametroDeBusca).list();
    }
    }
