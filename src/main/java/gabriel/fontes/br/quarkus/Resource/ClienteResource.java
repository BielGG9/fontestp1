package gabriel.fontes.br.quarkus.Resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

import gabriel.fontes.br.quarkus.Dto.ClienteRequest;
import gabriel.fontes.br.quarkus.Dto.ClienteResponse;
import gabriel.fontes.br.quarkus.Service.ClienteService;


@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteService service;

    @GET
    @RolesAllowed({"USER", "ADM"})
    public List<ClienteResponse> findAll() {
        return service.findAll();

    }

    @GET
    @Path("/meu-perfil")
    @RolesAllowed({"USER", "ADM"})
    public Response getMeuPerfil() {
        ClienteResponse meuPerfil = service.getMeuPerfil();
        return Response.ok(meuPerfil).build();

    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"USER", "ADM"})
    public ClienteResponse findById(@PathParam("id") Long id) {
        return service.findById(id);

    }

    @POST
    @Transactional
    @RolesAllowed("ADM")
    public Response create(ClienteRequest request) {
        ClienteResponse response = service.create(request);
        return Response.created(URI.create("/clientes/" + response.id())).entity(response).build();

    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed("ADM")
    public ClienteResponse update(@PathParam("id")Long id, ClienteRequest request) {
        return service.update(id, request);

    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed("ADM")
    public Response delete(@PathParam("id") Long id) {
        ClienteResponse clienteDeletada = service.delete(id);
        return Response.ok(clienteDeletada).build();

    }
}
