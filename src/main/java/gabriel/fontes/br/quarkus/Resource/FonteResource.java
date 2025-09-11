package gabriel.fontes.br.quarkus.Resource;

import gabriel.fontes.br.quarkus.Dto.FonteRequest;
import gabriel.fontes.br.quarkus.Model.Fonte;
import gabriel.fontes.br.quarkus.Repository.FonteRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.decorator.Delegate;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/fontes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FonteResource {

    private FonteRepository repository;

    @Inject
    public FonteResource(FonteRepository repository) {
        this.repository = repository;
    }

    @POST
    @Transactional
    public Response cadastrarFonte(FonteRequest fonteRequest) {
        Fonte fonte = new Fonte();
        fonte.setNome(fonteRequest.getNome());
        fonte.setPotencia(fonteRequest.getPotencia());

        repository.persist(fonte);
        return Response.status(Response.Status.CREATED).entity(fonte).build();
    }

    @GET    
    public Response listarFontes() {
        PanacheQuery<Fonte> fonteService = repository.findAll();
        return Response.ok(fonteService.list()).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletarFonte(@PathParam("id") Long id) {
        Fonte fonte = repository.findById(id);
        if (fonte != null) {
            repository.delete(fonte);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizarFonte(@PathParam("id") Long id, FonteRequest fonteRequest) {
        Fonte fonte = repository.findById(id);
        if (fonte != null) {
            fonte.setNome(fonteRequest.getNome());
            fonte.setPotencia(fonteRequest.getPotencia());
            repository.persist(fonte);
            return Response.ok(fonte).build();
        }
        
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
