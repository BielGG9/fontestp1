package gabriel.fontes.br.quarkus.Model;

import gabriel.fontes.br.quarkus.Model.Enums.Cargo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Funcionario extends Pessoa {


    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    public Funcionario() {
    }

    public Cargo getCargo() {
        return cargo;
    }
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
