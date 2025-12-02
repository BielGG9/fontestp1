package gabriel.fontes.br.quarkus.Resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

import gabriel.fontes.br.quarkus.Dto.PedidoRequest;
import gabriel.fontes.br.quarkus.Dto.PedidoResponse;
import gabriel.fontes.br.quarkus.Service.PedidoService;


@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService service;

    @GET
    @RolesAllowed({"USER", "ADM"})
    public List<PedidoResponse> findAll() {
        return service.findAll();

    }

    @GET 
    @Path("/{id}")
    @RolesAllowed({"USER", "ADM"})
    public PedidoResponse findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/historico/{id}")
    @RolesAllowed({"USER", "ADM"})
    public Response buscarHistoricoPedido(
    @QueryParam("nomeCliente") String nomeCliente,
    @QueryParam("fonteId") Long fonteId,
    @QueryParam("itensPedidoId") Long ItensPedidoId) {
    
    List<PedidoResponse> buscarHistoricoPedido = service.buscarHistoricoPedido(nomeCliente, fonteId, ItensPedidoId);
        return Response.ok(buscarHistoricoPedido).build();

    }

    @POST
    @Transactional
    @RolesAllowed({"USER", "ADM"})
    public Response create(PedidoRequest request) {
        PedidoResponse response = service.create(request);
        return Response.created(URI.create("/pedidos/" + response.id())).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"USER", "ADM"})
    public PedidoResponse update(@PathParam("id")Long id, PedidoRequest request) {
        return service.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed("ADM")
    public PedidoResponse delete(@PathParam("id") Long id) {
        return service.delete(id);
    }

}
