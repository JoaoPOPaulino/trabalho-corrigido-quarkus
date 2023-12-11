package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.pagamento.PagamentoDTO;
import br.unitins.topicos1.model.TipoPagamento;
import br.unitins.topicos1.service.pagamento.PagamentoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/pagamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoResource {

    @Inject
    PagamentoService service;

    private static final Logger LOGGER = Logger.getLogger(PagamentoResource.class.getName());

    @POST
    @RolesAllowed({ "User", "Admin" })
    public Response insert(PagamentoDTO dto) {
        LOGGER.info("Inserindo novo pagamento");
        Response response = Response.status(Status.CREATED).entity(service.insert(dto)).build();
        LOGGER.info("Pagamento inserido com sucesso");
        return response;
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({ "User", "Admin" })
    public Response update(PagamentoDTO dto, @PathParam("id") Long id) {
        LOGGER.info("Atualizando pagamento com ID: " + id);
        service.update(dto, id);
        LOGGER.info("Pagamento com ID: " + id + " atualizado com sucesso");
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Excluindo pagamento com ID: " + id);
        service.delete(id);
        LOGGER.info("Pagamento com ID: " + id + " excluído com sucesso");
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Buscando pagamento com ID: " + id);
        Response response = Response.ok(service.findById(id)).build();
        LOGGER.info("Pagamento com ID: " + id + " encontrado com sucesso");
        return response;
    }

    @GET
    @RolesAllowed({ "Admin" })
    public Response findAll() {
        LOGGER.info("Buscando todos os pagamentos");
        Response response = Response.ok(service.findByAll()).build();
        LOGGER.info("Pagamentos recuperados com sucesso");
        return response;
    }

    @GET
    @Path("/search/tipoPagamento/{tipoPagamento}")
    @RolesAllowed({ "User", "Admin" })
    public Response findByTipoPagamento(@PathParam("tipoPagamento") TipoPagamento tipoPagamento) {
        LOGGER.info("Buscando pagamentos pelo tipo: " + tipoPagamento);
        Response response = Response.ok(service.findByTipoPagamento(tipoPagamento)).build();

        LOGGER.info("Pagamento do tipo: " + tipoPagamento + " recuperados com sucesso");
        return response;
    }

}