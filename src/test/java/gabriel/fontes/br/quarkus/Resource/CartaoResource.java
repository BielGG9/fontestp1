package gabriel.fontes.br.quarkus.Resource;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import gabriel.fontes.br.quarkus.Dto.CartaoRequest;
import gabriel.fontes.br.quarkus.Dto.CartaoResponse;
import gabriel.fontes.br.quarkus.Service.CartaoService; // Crie essa interface/service se não tiver

@Path("/cartoes") // <--- O TESTE PROCURA EXATAMENTE ESSE CAMINHO
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartaoResource {

    @Inject
    CartaoService cartaoService;

    @GET
    @RolesAllowed({"USER", "ADM"})
    public List<CartaoResponse> findAll() {
        return cartaoService.findAll();
    }

    @POST
    @Transactional
    @RolesAllowed({"USER", "ADM"})
    public Response create(CartaoRequest dto) {
        CartaoResponse cartao = cartaoService.create(dto);
        return Response.status(Status.CREATED).entity(cartao).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"USER", "ADM"})
    public Response findById(@PathParam("id") Long id) {
        try {
            return Response.ok(cartaoService.findById(id)).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"USER", "ADM"})
    public Response delete(@PathParam("id") Long id) {
        try {
            cartaoService.delete(id);
            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}