package gabriel.fontes.br.quarkus.Model;

import java.time.LocalDateTime;


import gabriel.fontes.br.quarkus.Model.Abstratc.DefaultEntity;
import gabriel.fontes.br.quarkus.Model.Enums.TipoPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pagamento extends DefaultEntity{
    
    private LocalDateTime dataPagamento;

    private Double valor;

    private TipoPagamento tipoPagamento;

    public Pagamento() {
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}
