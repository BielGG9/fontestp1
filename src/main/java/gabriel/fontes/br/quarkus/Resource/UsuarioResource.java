package gabriel.fontes.br.quarkus.Resource;

import java.util.List;
import gabriel.fontes.br.quarkus.Dto.UsuarioRequest;
import gabriel.fontes.br.quarkus.Dto.UsuarioResponse;
import gabriel.fontes.br.quarkus.Service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    
    @Inject
    UsuarioService service;

    @POST
    @RolesAllowed("ADM")
    public Response create(UsuarioRequest request) {
        UsuarioResponse response = service.create(request);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    @RolesAllowed("ADM") 
    public List<UsuarioResponse> findAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADM", "USER"}) 
    public Response findById(@PathParam("id") Long id) {
        UsuarioResponse response = service.findById(id);
        return Response.ok(response).build();
}
}