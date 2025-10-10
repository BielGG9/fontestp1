package gabriel.fontes.br.quarkus.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import gabriel.fontes.br.quarkus.Model.Abstratc.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "cliente")
public class Cliente extends PessoaFisica {

    private LocalDateTime dataCadastro = LocalDateTime.now();
    

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
