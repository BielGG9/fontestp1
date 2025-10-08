package gabriel.fontes.br.quarkus.Model.Enums;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  StatusDepartamento{
    
    ATIVO(1, "Ativo"),
    INATIVO(2, "Inativo");

    @JsonProperty("id")
    private final int id;

    @JsonProperty("nome")
    private final String nome;

    StatusDepartamento(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static StatusDepartamento getById(int id) {
        return Stream.of(StatusDepartamento.values())
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ID da certificação inválido: " + id));
    }
}