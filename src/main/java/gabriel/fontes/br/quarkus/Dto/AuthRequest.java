package gabriel.fontes.br.quarkus.Dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest (
 
    @NotBlank(message = "O login não pode ser vazio")
    String login,

    @NotBlank(message = "A senha não pode ser vazio")
    String senha

){}
