package gabriel.fontes.br.quarkus.Resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import gabriel.fontes.br.quarkus.Dto.EnderecoRequest;
import gabriel.fontes.br.quarkus.Service.EnderecoService;

@Path("/clientes/{clienteId}/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService service;

    @POST
    public Response create(@PathParam("clienteId") Long clienteId, EnderecoRequest dto) {
        var response = service.create(clienteId, dto);
        return Response.status(Status.CREATED).entity(response).build();
    }

    @PUT
    @Path("/{enderecoId}")
    public Response update(@PathParam("clienteId") Long clienteId, @PathParam("enderecoId") Long enderecoId, EnderecoRequest dto) {
        var response = service.update(clienteId, enderecoId, dto);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{enderecoId}")
    public Response delete(@PathParam("clienteId") Long clienteId, @PathParam("enderecoId") Long enderecoId) {
        service.delete(clienteId, enderecoId);
        return Response.noContent().build();
    }

    @GET
    public Response findByClienteId(@PathParam("clienteId") Long clienteId) {
        var response = service.findByClienteId(clienteId);
        return Response.ok(response).build();
    }

    @GET
    @Path("/{enderecoId}")
    public Response findById(@PathParam("clienteId") Long clienteId, @PathParam("enderecoId") Long enderecoId) {
        var response = service.findById(clienteId, enderecoId);
        return Response.ok(response).build();
    }
}