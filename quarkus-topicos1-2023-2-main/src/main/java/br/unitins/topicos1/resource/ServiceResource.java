package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.servico.ServicoDTO;
import br.unitins.topicos1.service.servico.ServicoService;
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

import org.jboss.logging.Logger;

@Path("/servico")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceResource {

    private static final Logger LOGGER = Logger.getLogger(ServiceResource.class.getName());

    @Inject
    ServicoService service;

    @POST
    public Response insert(ServicoDTO dto) {
        LOGGER.info("Iniciando inserção de novo serviço");
        Response response = Response.status(Status.CREATED).entity(service.insert(dto)).build();
        LOGGER.info("Serviço inserido com sucesso");
        return response;
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(ServicoDTO dto, @PathParam("id") Long id) {
        LOGGER.info("Iniciando atualização do serviço com ID: " + id);
        service.update(dto, id);
        LOGGER.info("Serviço com ID: " + id + " atualizado com sucesso");
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Iniciando remoção do serviço com ID: " + id);
        service.delete(id);
        LOGGER.info("Serviço com ID: " + id + " removido com sucesso");
        return Response.noContent().build();
    }

    @GET
    public Response findAll() {
        LOGGER.info("Buscando todos os serviços");
        Response response = Response.ok(service.findAll()).build();
        LOGGER.info("Serviços recuperados com sucesso");
        return response;
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Buscando serviço com ID: " + id);
        Response response = Response.ok(service.findById(id)).build();
        LOGGER.info("Serviço com ID: " + id + " recuperado com sucesso");
        return response;
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOGGER.info("Buscando serviço pelo nome: " + nome);
        Response response = Response.ok(service.findByNome(nome)).build();
        LOGGER.info("Serviços com nome: " + nome + " recuperados com sucesso");
        return response;
    }
}