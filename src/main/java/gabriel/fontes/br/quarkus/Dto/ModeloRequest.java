package gabriel.fontes.br.quarkus.Dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ModeloRequest(

    int numeracao,

    @NotNull(message = "O id da marca não pode ser nulo")
    @Positive(message = "O id da marca deve ser um número positivo")
    Long idMarca
    
) {


    
}
