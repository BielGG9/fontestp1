package gabriel.fontes.br.quarkus.Model.Enums;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  Cargo{
    
    VENDEDOR(1, "Vendedor"),
    GERENTE(2, "Gerente"),
    SUPERVISOR(3, "Supervisor");

    @JsonProperty("id")
    private final int id;

    @JsonProperty("nome")
    private final String nome;

    Cargo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static Cargo getById(int id) {
        return Stream.of(Cargo.values())
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ID da certificação inválido: " + id));
    }
}