package gabriel.fontes.br.quarkus.Model;

import gabriel.fontes.br.quarkus.Model.Abstratc.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {


    public Funcionario() {
    }
}
