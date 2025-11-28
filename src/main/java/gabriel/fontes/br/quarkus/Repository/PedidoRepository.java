package gabriel.fontes.br.quarkus.Repository;

import java.util.List;
import java.util.Optional;

import gabriel.fontes.br.quarkus.Model.Endereco;
import gabriel.fontes.br.quarkus.Model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {
    
    public List<Pedido> findByUsuarioId(String usuarioId) {
        return find("idUsuario", usuarioId).list();
    }

    public Optional<Endereco> findByIdUsuario(String idUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByIdUsuario'");
    }
}
