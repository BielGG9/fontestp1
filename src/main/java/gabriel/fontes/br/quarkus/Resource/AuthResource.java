package gabriel.fontes.br.quarkus.Resource;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import gabriel.fontes.br.quarkus.Model.Funcionario;
import gabriel.fontes.br.quarkus.Repository.FuncionarioRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.smallrye.jwt.build.Jwt;
import jakarta.ws.rs.NotAuthorizedException;
import io.quarkus.elytron.security.common.BcryptUtil;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    FuncionarioRepository funcionarioRepository;

    public static class LoginRequest {
        public String email;
        public String password; 
    }

   @POST
    @Path("/login")
    public Response login(LoginRequest loginRequest) {
        
        System.out.println("\n\n--- [DEBUG] TENTATIVA DE LOGIN ---");
        System.out.println("Email recebido: " + loginRequest.email);
        

        String senhaRecebida = loginRequest.password.trim(); 

        System.out.println("Senha recebida (raw): '" + loginRequest.password + "' (Comprimento: " + loginRequest.password.length() + ")");
        System.out.println("Senha recebida (limpa): '" + senhaRecebida + "' (Comprimento: " + senhaRecebida.length() + ")");
        


        Funcionario func = funcionarioRepository.find("email", loginRequest.email).firstResult();

        if (func == null) {
            System.out.println("RESULTADO: ERRO. Funcionário com email '" + loginRequest.email + "' NÃO encontrado no banco.");
            throw new NotAuthorizedException("Usuário ou senha inválidos (Funcionario nao encontrado)");
        }

        System.out.println("Funcionário encontrado: " + func.getNome());
        System.out.println("Hash no banco de dados: " + func.getPassword());
        
        if (func.getPassword() == null || func.getPassword().isEmpty()) {
            System.out.println("RESULTADO: ERRO. O funcionário foi encontrado, mas a senha no banco está VAZIA.");
            throw new NotAuthorizedException("Usuário ou senha inválidos (Senha no DB esta nula)");
        }
        
        boolean passwordsBatem = BcryptUtil.matches(senhaRecebida, func.getPassword());
        System.out.println("Passwords batem (Bcrypt): " + passwordsBatem);

        if (passwordsBatem) {
            Set<String> roles = new HashSet<>();
            roles.add(func.getCargo()); 
            String token = Jwt.issuer("https://fontes.br/api") 
                                .subject(func.getEmail()) 
                                .groups(roles) 
                                .expiresIn(Duration.ofHours(8)) 
                                .sign(); 
            System.out.println("RESULTADO: SUCESSO. Token gerado.");
            return Response.ok(token).build();
        }

        System.out.println("RESULTADO: ERRO. A senha recebida não bate com o hash do banco.");
        throw new NotAuthorizedException("Usuário ou senha inválidos (Senha incorreta)");
    }
}