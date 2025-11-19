package gabriel.fontes.br.quarkus.Dto;

import gabriel.fontes.br.quarkus.Model.Usuario;

public record UsuarioResponse(
    Long id, 
    String nome,
    String login,
    String perfil
) {
    
    public static UsuarioResponse fromEntity(Usuario usuario) {
        return new UsuarioResponse(
        usuario.getId(),
        usuario.getNome(),
        usuario.getLogin(),
        usuario.getPerfil().name()
    );
    } 
}
