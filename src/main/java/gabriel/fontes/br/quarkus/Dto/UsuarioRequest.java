package gabriel.fontes.br.quarkus.Dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequest(
    @NotBlank(message = "O nome não pode ser vazio")
    String nome,

    @NotBlank(message = "O login não pode ser vazio")
    String login,

    @NotBlank(message = "A senha não pode ser vazio")
    String senha,

    String perfil

) {
    
}
