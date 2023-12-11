package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.service.usuario.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
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
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());

    @Inject
    UsuarioService service;

    @POST
    @RolesAllowed({ "User", "Admin" })
    public Response insert(UsuarioDTO dto) {
        LOGGER.info("Iniciando inserção de novo usuário");
        Response response = Response.status(Status.CREATED).entity(service.insert(dto)).build();
        LOGGER.info("Usuário inserido com sucesso");
        return response;
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({ "User", "Admin" })
    public Response update(UsuarioDTO dto, @PathParam("id") Long id) {
        LOGGER.info("Iniciando atualização do usuário com ID: " + id);
        service.update(dto, id);
        LOGGER.info("Usuário com ID: " + id + " atualizado com sucesso");
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Iniciando remoção do usuário com ID: " + id);
        service.delete(id);
        LOGGER.info("Usuário com ID: " + id + " removido com sucesso");
        return Response.noContent().build();
    }

    @GET
    @RolesAllowed({ "Admin" })
    public Response findAll() {
        LOGGER.info("Buscando todos os usuários");
        Response response = Response.ok(service.findByAll()).build();
        LOGGER.info("Usuários recuperados com sucesso");
        return response;
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Buscando usuário com ID: " + id);
        Response response = Response.ok(service.findById(id)).build();
        LOGGER.info("Usuário com ID: " + id + " recuperado com sucesso");
        return response;
    }

    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({ "Admin" })
    public Response findByNome(@PathParam("nome") String nome) {
        LOGGER.info("Buscando usuário pelo nome: " + nome);
        Response response = Response.ok(service.findByNome(nome)).build();
        LOGGER.info("Usuários com nome: " + nome + " recuperados com sucesso");
        return response;
    }
}