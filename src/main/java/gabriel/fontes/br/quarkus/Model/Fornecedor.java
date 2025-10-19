package gabriel.fontes.br.quarkus.Model;

import gabriel.fontes.br.quarkus.Model.Abstratc.Pessoa;
import gabriel.fontes.br.quarkus.Model.Jpa.PessoaJuridica;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "fornecedores")
public class Fornecedor extends PessoaJuridica{


    private String razaoSocial;
    
    public Fornecedor() {
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }

}
