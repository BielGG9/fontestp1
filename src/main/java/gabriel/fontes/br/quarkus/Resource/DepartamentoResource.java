package gabriel.fontes.br.quarkus.Resource;

import gabriel.fontes.br.quarkus.Service.DepartamentoService;
import gabriel.fontes.br.quarkus.Dto.DepartamentoRequest;
import gabriel.fontes.br.quarkus.Dto.DepartamentoResponse;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/departamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartamentoResource {

    @Inject
    DepartamentoService service;

    @POST
    @Transactional
    public Response cadastrarDepartamento(DepartamentoRequest departamentoRequest) {

        DepartamentoResponse departamentoCriado = service.create(departamentoRequest);
        return Response.status(Response.Status.CREATED).entity(departamentoCriado).build();
    }

    @GET
    public Response listarDepartamentos() {

        List<DepartamentoResponse> lista = service.findAll();
        return Response.ok(lista).build();
    }
    
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {

        DepartamentoResponse departamento = service.findById(id);
        return Response.ok(departamento).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletarDepartamento(@PathParam("id") Long id) {

        DepartamentoResponse departamentoDeletado = service.delete(id);
        return Response.ok(departamentoDeletado).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizarDepartamento(@PathParam("id") Long id, DepartamentoRequest departamentoRequest) {

        DepartamentoResponse departamentoAtualizado = service.update(id, departamentoRequest);
        return Response.ok(departamentoAtualizado).build();
    }
}