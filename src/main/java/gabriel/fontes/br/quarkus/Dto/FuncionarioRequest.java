package gabriel.fontes.br.quarkus.Dto;

import jakarta.validation.constraints.NotBlank;

public record FuncionarioRequest(
     @NotBlank(message = "O nome do funcionário é obrigatório")
    String nome,
    
    @NotBlank(message = "O cargo do funcionário é obrigatório")
    String cargo
) {}
