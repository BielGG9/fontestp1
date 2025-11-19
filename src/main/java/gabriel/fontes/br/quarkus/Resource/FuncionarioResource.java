package gabriel.fontes.br.quarkus.Resource;


import gabriel.fontes.br.quarkus.Service.FuncionarioService;
import io.quarkus.security.Authenticated;
import gabriel.fontes.br.quarkus.Dto.FuncionarioRequest;
import gabriel.fontes.br.quarkus.Dto.FuncionarioResponse;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioResource {

    @Inject
    FuncionarioService service;

    @POST
    @Transactional
    public Response cadastrarFuncionario(FuncionarioRequest funcionarioRequest) {

        FuncionarioResponse funcionarioCriado = service.create(funcionarioRequest);
        return Response.status(Response.Status.CREATED).entity(funcionarioCriado).build();
    }

    @GET
    public Response listarFuncionarios() {

        List<FuncionarioResponse> lista = service.findAll();
        return Response.ok(lista).build();
    }
    
    @GET
        @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {

        FuncionarioResponse funcionario = service.findById(id);
        return Response.ok(funcionario).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletarFuncionario(@PathParam("id") Long id) {

        FuncionarioResponse funcionarioDeletado = service.delete(id);
        return Response.ok(funcionarioDeletado).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizarFuncionario(@PathParam("id") Long id, FuncionarioRequest funcionarioRequest) {

        FuncionarioResponse funcionarioAtualizado = service.update(id, funcionarioRequest);
        return Response.ok(funcionarioAtualizado).build();
    }
}