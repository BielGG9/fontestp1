package gabriel.fontes.br.quarkus.Model;

import gabriel.fontes.br.quarkus.Model.Abstratc.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;



@Entity
@Table(name = "fornecedores")
public class Fornecedor extends Pessoa {


    private String razaoSocial;
    private String cnpj;

    public Fornecedor() {
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getCnpj() {
        return cnpj;
    }

}
