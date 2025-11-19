package gabriel.fontes.br.quarkus.Resource;


import gabriel.fontes.br.quarkus.Service.TelefoneService;
import io.quarkus.security.Authenticated;
import gabriel.fontes.br.quarkus.Dto.TelefoneRequest;
import gabriel.fontes.br.quarkus.Dto.TelefoneResponse;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/telefones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneResource {

    @Inject
    TelefoneService service;

    @POST
    @Transactional
    public Response cadastrarTelefone(TelefoneRequest telefoneRequest) {

        TelefoneResponse telefoneCriado = service.create(telefoneRequest);
        return Response.status(Response.Status.CREATED).entity(telefoneCriado).build();
    }

    @GET
    public Response listarTelefones() {

        List<TelefoneResponse> lista = service.findAll();
        return Response.ok(lista).build();
    }
    
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {

        TelefoneResponse telefone = service.findById(id);
        return Response.ok(telefone).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletarTelefone(@PathParam("id") Long id) {

        TelefoneResponse telefoneDeletado = service.delete(id);
        return Response.ok(telefoneDeletado).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizarTelefone(@PathParam("id") Long id, TelefoneRequest telefoneRequest) {

        TelefoneResponse telefoneAtualizado = service.update(id, telefoneRequest);
        return Response.ok(telefoneAtualizado).build();
    }
}