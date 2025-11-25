package gabriel.fontes.br.quarkus.Resource;

import gabriel.fontes.br.quarkus.Dto.AuthRequest;
import gabriel.fontes.br.quarkus.Dto.AuthResponse;
import gabriel.fontes.br.quarkus.Model.Usuario;
import gabriel.fontes.br.quarkus.Repository.UsuarioRepository;
import gabriel.fontes.br.quarkus.Service.HashService;
import gabriel.fontes.br.quarkus.Service.TokenService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.NotAuthorizedException;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    HashService hashService;

    @Inject
    TokenService tokenService;

   @POST
    @Path("/login")
    public Response Login(@Valid AuthRequest authRequest) {

        Usuario usuario = usuarioRepository.findBylogin(authRequest.login())
                .orElseThrow(() -> new NotAuthorizedException("Login Invalido! "));

        if (hashService.verificarSenha(authRequest.senha(), usuario.getSenha())) {
            
            String token = tokenService.gerarToken(usuario);
            
            AuthResponse response = AuthResponse.fromEntity(token, usuario);
            
            return Response.ok(response).build();
        }

        throw new NotAuthorizedException("Usuario ou senha Invalidos! ");
        
    }
}