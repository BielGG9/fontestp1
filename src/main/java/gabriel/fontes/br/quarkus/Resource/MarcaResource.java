package gabriel.fontes.br.quarkus.Resource;

import gabriel.fontes.br.quarkus.Dto.MarcaRequest;
import gabriel.fontes.br.quarkus.Dto.MarcaResponse;
import gabriel.fontes.br.quarkus.Service.MarcaService;
import jakarta.annotation.security.RolesAllowed;
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
public class MarcaResource {

    @Inject
    MarcaService service;

    @GET
    @RolesAllowed({"USER", "ADM"})
    public List<MarcaResponse> findAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"USER", "ADM"})
    public MarcaResponse findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    @Transactional
    @RolesAllowed("ADM")
    public Response create(MarcaRequest request) {
        MarcaResponse response = service.create(request);
        return Response.created(URI.create("/marcas/" + response.id())).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed("ADM")
    public MarcaResponse update(@PathParam("id") Long id, MarcaRequest request) {
        return service.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed("ADM")
    public Response delete(@PathParam("id") Long id) {
        MarcaResponse marcaDeletada = service.delete(id);
        return Response.ok(marcaDeletada).build();
    }
}