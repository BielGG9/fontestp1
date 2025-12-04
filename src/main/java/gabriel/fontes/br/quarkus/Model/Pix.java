package gabriel.fontes.br.quarkus.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class Pix extends Pagamento{
    

    private String chavePix;
    private LocalDateTime validade;

    public Pix() {
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public LocalDateTime getValidade() {
        return validade;
    }

    public void setValidade(LocalDateTime validade) {
        this.validade = validade;
    }

}
