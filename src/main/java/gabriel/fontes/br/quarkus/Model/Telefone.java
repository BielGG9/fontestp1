package gabriel.fontes.br.quarkus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gabriel.fontes.br.quarkus.Model.Abstratc.DefaultEntity;
import gabriel.fontes.br.quarkus.Model.Abstratc.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "telefones")
public class Telefone extends Pessoa{


    private String numero;
    private String ddd;

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

}