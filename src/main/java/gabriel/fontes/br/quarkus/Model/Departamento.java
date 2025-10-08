package gabriel.fontes.br.quarkus.Model;

import gabriel.fontes.br.quarkus.Model.Abstratc.DefaultEntity;
import gabriel.fontes.br.quarkus.Model.Enums.StatusDepartamento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamento")   
public class Departamento extends DefaultEntity {



    private String sigla;
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusDepartamento statusDepartamento;

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
    public void setStatusDepartamento(Object statusDepartamento) {
        this.statusDepartamento = statusDepartamento;
    }
    public static Object valueOf(String upperCase) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'valueOf'");
    }

}
