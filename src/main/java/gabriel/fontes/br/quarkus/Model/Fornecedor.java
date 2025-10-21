package gabriel.fontes.br.quarkus.Model;

import java.util.ArrayList;
import java.util.List;

import gabriel.fontes.br.quarkus.Model.Abstratc.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;



@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Fornecedor extends PessoaJuridica{


    private String razaoSocial;

    @ManyToMany(mappedBy = "fornecedores")
    private List<Fonte> fontes;

    public Fornecedor() {
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }

}
