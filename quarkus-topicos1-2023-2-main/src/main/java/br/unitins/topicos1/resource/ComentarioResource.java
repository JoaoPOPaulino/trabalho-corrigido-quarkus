package br.unitins.topicos1.resource;

import java.time.LocalDateTime;
import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.comentario.ComentarioDTO;
import br.unitins.topicos1.dto.comentario.ComentarioResponseDTO;
import br.unitins.topicos1.service.comentario.ComentarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/comentarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComentarioResource {

    @Inject
    ComentarioService service;

    private static final Logger LOGGER = Logger.getLogger(ComentarioResource.class.getName());

    @POST
    @RolesAllowed({ "User", "Admin" })
    public Response insert(ComentarioDTO dto) {
        LOGGER.info("Inserindo novo comentário");
        Response response = Response.status(Status.CREATED).entity(service.insert(dto)).build();
        LOGGER.info("Comentário inserido com sucesso");
        return response;
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({ "User", "Admin" })
    public Response update(ComentarioDTO dto, @PathParam("id") Long id) {
        LOGGER.info("Atualizando comentário com ID: " + id);
        try {
            ComentarioResponseDTO dtoUpdate = service.update(dto, id);
            LOGGER.info("Comentário com ID: " + id + " atualizado com sucesso");
            return Response.ok(dtoUpdate).build();
        } catch (NotFoundException e) {
            LOGGER.error("Comentário não encontrado para atualização com ID: " + id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({ "User", "Admin" })
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Excluindo comentário com ID: " + id);
        service.delete(id);
        LOGGER.info("Comentário com ID: " + id + " excluído com sucesso");
        return Response.noContent().build();
    }

    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response findAll() {
        LOGGER.info("Buscando todos os comentários");
        Response response = Response.ok(service.findAll()).build();
        LOGGER.info("Comentários recuperados com sucesso");
        return response;
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Buscando comentário com ID: " + id);
        ComentarioResponseDTO dto = service.findById(id);
        if (dto == null) {
            LOGGER.error("Comentário não encontrado com ID: " + id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        LOGGER.info("Comentário com ID: " + id + " encontrado com sucesso");
        return Response.ok(dto).build();
    }

    @GET
    @Path("/data")
    @RolesAllowed({ "User", "Admin" })
    public Response findComentariosByData(@QueryParam("data") String dataStr) {
        LOGGER.info("Buscando comentários pela data: " + dataStr);
        try {
            LocalDateTime data = LocalDateTime.parse(dataStr);
            List<ComentarioResponseDTO> comentarios = service.findComentariosByData(data);
            LOGGER.info("Comentários recuperados com sucesso para a data: " + dataStr);
            return Response.ok(comentarios).build();
        } catch (Exception e) {
            LOGGER.error("Erro ao buscar comentários pela data: " + dataStr + " - " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Data inválida ou formato incorreto").build();
        }
    }

}