package gabriel.fontes.br.quarkus.Resource;


import gabriel.fontes.br.quarkus.Service.PessoaFisicaService;
import gabriel.fontes.br.quarkus.Dto.PessoaFisicaRequest;
import gabriel.fontes.br.quarkus.Dto.PessoaFisicaResponse;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/pessoas-fisicas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaFisicaResource {

    @Inject
    PessoaFisicaService service;

    @POST
    @Transactional
    public Response cadastrarPessoaFisica(PessoaFisicaRequest pessoaFisicaRequest) {

        PessoaFisicaResponse pessoaFisicaCriada = service.create(pessoaFisicaRequest);
        return Response.status(Response.Status.CREATED).entity(pessoaFisicaCriada).build();
    }

    @GET
    public Response listarPessoasFisicas() {

        List<PessoaFisicaResponse> lista = service.findAll();
        return Response.ok(lista).build();
    }
    
    @GET
        @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {

        PessoaFisicaResponse pessoaFisica = service.findById(id);
        return Response.ok(pessoaFisica).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletarPessoaFisica(@PathParam("id") Long id) {

        PessoaFisicaResponse pessoaFisicaDeletada = service.delete(id);
        return Response.ok(pessoaFisicaDeletada).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizarPessoaFisica(@PathParam("id") Long id, PessoaFisicaRequest pessoaFisicaRequest) {

        PessoaFisicaResponse pessoaFisicaAtualizada = service.update(id, pessoaFisicaRequest);
        return Response.ok(pessoaFisicaAtualizada).build();
    }
}