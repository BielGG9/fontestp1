package gabriel.fontes.br.quarkus.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record FonteRequest(

    @NotBlank(message = "O nome deve conter ao menos 1 caractere")
    String nome,

    @NotBlank(message = "A potência deve ser um número positivo")
    Integer potencia,


    @Positive(message = "O preço deve ser um número positivo")
    double preco,

    @NotNull(message = "O fabricante deve conter ao menos 1 caractere")
    String fabricante
    
) {}
 