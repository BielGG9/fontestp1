package gabriel.fontes.br.quarkus.Model;

import gabriel.fontes.br.quarkus.Model.Abstratc.DefaultEntity;
import gabriel.fontes.br.quarkus.Model.Enums.StatusDepartamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamento")   
public class Departamento extends DefaultEntity {



    private String sigla;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusDepartamento statusDepartamento;

    @ManyToOne(optional = false)
    private Funcionario funcionario;

    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public StatusDepartamento getStatusDepartamento() {
        return statusDepartamento;
    }
    public void setStatusDepartamento(StatusDepartamento statusDepartamento) {
        this.statusDepartamento = statusDepartamento;
    }

}
