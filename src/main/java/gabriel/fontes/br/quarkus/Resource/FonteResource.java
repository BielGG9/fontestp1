package gabriel.fontes.br.quarkus.Resource;

import gabriel.fontes.br.quarkus.Service.FonteService;
import gabriel.fontes.br.quarkus.Dto.FonteRequest;
import gabriel.fontes.br.quarkus.Dto.FonteResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/fontes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FonteResource {

    @Inject
    FonteService service;

    @POST
    @Transactional
    @RolesAllowed("ADM")
    public Response cadastrarFonte(FonteRequest fonteRequest) {
       
        FonteResponse fonteCriada = service.create(fonteRequest);
        return Response.status(Response.Status.CREATED).entity(fonteCriada).build();
    }

    @GET
    @RolesAllowed({"USER", "ADM"})
    public Response FindAll() {

        List<FonteResponse> lista = service.findAll();
        return Response.ok(lista).build();
    }
    
    @GET
    @Path("/{id}")
    @RolesAllowed({"USER", "ADM"})
    public Response findById(@PathParam("id") Long id) {

        FonteResponse fonte = service.findById(id);
        return Response.ok(fonte).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed("ADM")
    public Response delete(@PathParam("id") Long id) {

        FonteResponse fonteDeletada = service.delete(id);
        return Response.ok(fonteDeletada).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed("ADM")
    public Response update(@PathParam("id") Long id, FonteRequest fonteRequest) {

        FonteResponse fonteAtualizada = service.update(id, fonteRequest);
        return Response.ok(fonteAtualizada).build();
    }
}