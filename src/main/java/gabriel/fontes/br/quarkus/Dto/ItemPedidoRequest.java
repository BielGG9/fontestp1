package gabriel.fontes.br.quarkus.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoRequest(

    @NotNull(message = "A quantidade é obrigatória")
    @Positive(message = "A quantidade deve ser maior que zero")
    Integer quantidade,

    @NotBlank(message = "O preço não pode ser vazio")
    Double preco,

    @NotBlank(message = "A fonte é obrigatória")
    Long fonteId

) {
    
}
