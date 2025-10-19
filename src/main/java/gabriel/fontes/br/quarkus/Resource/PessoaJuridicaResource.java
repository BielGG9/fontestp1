package gabriel.fontes.br.quarkus.Resource;



import gabriel.fontes.br.quarkus.Service.PessoaJuridicaService;
import gabriel.fontes.br.quarkus.Dto.PessoaJuridicaRequest;
import gabriel.fontes.br.quarkus.Dto.PessoaJuridicaResponse;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/pessoas-juridicas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaJuridicaResource {

    @Inject
    PessoaJuridicaService service;

    @POST
    @Transactional
    public Response cadastrarPessoaJuridica(PessoaJuridicaRequest pessoaJuridicaRequest) {

        PessoaJuridicaResponse pessoaJuridicaCriada = service.create(pessoaJuridicaRequest);
        return Response.status(Response.Status.CREATED).entity(pessoaJuridicaCriada).build();
    }

    @GET
    public Response listarPessoasJuridicas() {

        List<PessoaJuridicaResponse> lista = service.findAll();
        return Response.ok(lista).build();
    }
    
    @GET
        @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {

        PessoaJuridicaResponse pessoaJuridica = service.findById(id);
        return Response.ok(pessoaJuridica).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletarPessoaJuridica(@PathParam("id") Long id) {

        PessoaJuridicaResponse pessoaJuridicaDeletada = service.delete(id);
        return Response.ok(pessoaJuridicaDeletada).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizarPessoaJuridica(@PathParam("id") Long id, PessoaJuridicaRequest pessoaJuridicaRequest) {

        PessoaJuridicaResponse pessoaJuridicaAtualizada = service.update(id, pessoaJuridicaRequest);
        return Response.ok(pessoaJuridicaAtualizada).build();
    }
}