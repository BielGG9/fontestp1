package gabriel.fontes.br.quarkus.Resource;

import gabriel.fontes.br.quarkus.Dto.MarcaRequest;
import gabriel.fontes.br.quarkus.Dto.MarcaResponse;
import gabriel.fontes.br.quarkus.Service.MarcaService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/marca")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class MarcaResource {

    @Inject
    MarcaService service;

    @GET
    public List<MarcaResponse> findAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public MarcaResponse findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    @Transactional
    public Response create(MarcaRequest request) {
        MarcaResponse response = service.create(request);
        return Response.created(URI.create("/marcas/" + response.id())).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public MarcaResponse update(@PathParam("id") Long id, MarcaRequest request) {
        return service.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        MarcaResponse marcaDeletada = service.delete(id);
        return Response.ok(marcaDeletada).build();
    }
}