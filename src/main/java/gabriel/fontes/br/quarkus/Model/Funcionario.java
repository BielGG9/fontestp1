package gabriel.fontes.br.quarkus.Model;

import gabriel.fontes.br.quarkus.Model.Abstratc.DefaultEntity;
import gabriel.fontes.br.quarkus.Model.Abstratc.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Funcionario extends PessoaFisica {


    private String cargo;

    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    public Funcionario() {
    }

    public Departamento getDepartamento() {
        return departamento;
    }
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    

}
