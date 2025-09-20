package gabriel.fontes.br.quarkus.Resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import gabriel.fontes.br.quarkus.Dto.TelefoneRequest;
import gabriel.fontes.br.quarkus.Service.TelefoneService;

@Path("/clientes/{clienteId}/telefones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneResource {

    @Inject
    TelefoneService service;

    @POST
    public Response create(@PathParam("clienteId") Long clienteId, TelefoneRequest dto) {
        var response = service.create(clienteId, dto);
        return Response.status(Status.CREATED).entity(response).build();
    }

    @PUT
    @Path("/{telefoneId}")
    public Response update(@PathParam("clienteId") Long clienteId, @PathParam("telefoneId") Long telefoneId, TelefoneRequest dto) {
        var response = service.update(clienteId, telefoneId, dto);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{telefoneId}")
    public Response delete(@PathParam("clienteId") Long clienteId, @PathParam("telefoneId") Long telefoneId) {
        service.delete(clienteId, telefoneId);
        return Response.noContent().build();
    }

    @GET
    public Response findByClienteId(@PathParam("clienteId") Long clienteId) {
        var response = service.findByClienteId(clienteId);
        return Response.ok(response).build();
    }

    @GET
    @Path("/{telefoneId}")
    public Response findById(@PathParam("clienteId") Long clienteId, @PathParam("telefoneId") Long telefoneId) {
        var response = service.findById(clienteId, telefoneId);
        return Response.ok(response).build();
    }
}   