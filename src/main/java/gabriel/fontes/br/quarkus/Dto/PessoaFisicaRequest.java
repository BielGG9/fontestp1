package gabriel.fontes.br.quarkus.Dto;

import jakarta.validation.constraints.NotBlank;

public record PessoaFisicaRequest(

    @NotBlank(message = "O campo CPF é obrigatório.")
    String cpf,

    @NotBlank(message = "O campo RG é obrigatório.")
    String rg
    
) {
    
}
