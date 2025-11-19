package gabriel.fontes.br.quarkus.Resource;


import gabriel.fontes.br.quarkus.Service.FornecedorService;
import io.quarkus.security.Authenticated;
import gabriel.fontes.br.quarkus.Dto.FornecedorRequest;
import gabriel.fontes.br.quarkus.Dto.FornecedorResponse;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/fornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    @Inject
    FornecedorService service;

    @POST
    @Transactional
    public Response cadastrarFornecedor(FornecedorRequest fornecedorRequest) {

        FornecedorResponse fornecedorCriado = service.create(fornecedorRequest);
        return Response.status(Response.Status.CREATED).entity(fornecedorCriado).build();
    }

    @GET
    public Response listarFornecedores() {

        List<FornecedorResponse> lista = service.findAll();
        return Response.ok(lista).build();
    }
    
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {

        FornecedorResponse fornecedor = service.findById(id);
        return Response.ok(fornecedor).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletarFornecedor(@PathParam("id") Long id) {

        FornecedorResponse fornecedorDeletado = service.delete(id);
        return Response.ok(fornecedorDeletado).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizarFornecedor(@PathParam("id") Long id, FornecedorRequest fornecedorRequest) {

        FornecedorResponse fornecedorAtualizado = service.update(id, fornecedorRequest);
        return Response.ok(fornecedorAtualizado).build();
    }
}