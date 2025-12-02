package gabriel.fontes.br.quarkus.Model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import gabriel.fontes.br.quarkus.Model.Abstratc.DefaultEntity;
import gabriel.fontes.br.quarkus.Model.Enums.TipoPagamento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido extends DefaultEntity{

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private Double total;

    // Define o ID do usuário do Keycloak associado ao pedido
    @Column(name = "id_usuario_keycloack")
    private String idUsuario;
    private String nomeClienteSnapshot;

    @Column(nullable = false)
    private TipoPagamento tipoPagamento;

    // Relação Um-para-Muitos com ItemPedido com cascade persist
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
    private List<ItemPedido> itensPedido;

    @Embedded
    private EnderecoEntrega enderecoEntrega;

    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNomeClienteSnapshot() {
        return nomeClienteSnapshot;
    }
    public void setNomeClienteSnapshot(String nomeClienteSnapshot) {
        this.nomeClienteSnapshot = nomeClienteSnapshot;
    }
    public EnderecoEntrega getEnderecoEntrega() {
        return enderecoEntrega;
    }
    public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega2) {
        this.enderecoEntrega = enderecoEntrega2;
    }
    public EnderecoEntrega EnderecoEntrega() {
        throw new UnsupportedOperationException("Erro ao converter pedido");
    }
    
    public Collection<ItemPedido> getItens() {
        return itensPedido;
    }
    public void setItens(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }
    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }
    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }   
}
