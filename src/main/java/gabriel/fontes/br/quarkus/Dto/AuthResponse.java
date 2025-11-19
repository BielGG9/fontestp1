package gabriel.fontes.br.quarkus.Dto;

import gabriel.fontes.br.quarkus.Model.Usuario;


public record AuthResponse(
    Long id,
    String token,
    String login,
    String senha,
    String Perfil
) {

    
    public static AuthResponse fromEntity(String token, Usuario usuario) {
        return new AuthResponse(
            usuario.getId(),
            token,
            usuario.getLogin(),
            usuario.getSenha(),
            usuario.getPerfil().name()
        );
    }
}
