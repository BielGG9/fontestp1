package gabriel.fontes.br.quarkus.Model;

import java.time.LocalDateTime;

public class Boleto extends Pagamento{
    
    private String codigoBarras;
    private LocalDateTime dataVencimento;

    public Boleto() {
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

}