package gabriel.fontes.br.quarkus.Resource;

import gabriel.fontes.br.quarkus.Dto.ModeloRequest;
import gabriel.fontes.br.quarkus.Dto.ModeloResponse;
import gabriel.fontes.br.quarkus.Service.ModeloService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/modelos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ModeloResource {

    @Inject
    ModeloService service;

    @GET
    @RolesAllowed({"USER", "ADM"})
    public List<ModeloResponse> findAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"USER", "ADM"})
    public ModeloResponse findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    @Transactional
    @RolesAllowed("ADM")
    public Response create(ModeloRequest request) {
        ModeloResponse response = service.create(request);
        return Response.created(URI.create("/modelos/" + response.id())).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed("ADM")
    public ModeloResponse update(@PathParam("id") Long id, ModeloRequest request) {
        return service.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed("ADM")
    public Response delete(@PathParam("id") Long id) {
        ModeloResponse modeloDeletado = service.delete(id);
        return Response.ok(modeloDeletado).build();
    }
}