package br.unitins.topicos1.resource;

import java.io.File;
import java.io.IOException;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.quarto.QuartoDTO;
import br.unitins.topicos1.dto.quarto.QuartoResponseDTO;
import br.unitins.topicos1.form.QuartoImageForm;
import br.unitins.topicos1.model.TipoQuarto;
import br.unitins.topicos1.service.quarto.QuartoFileService;
import br.unitins.topicos1.service.quarto.QuartoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/quartos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuartoResource {

    @Inject
    QuartoService service;

    @Inject
    QuartoFileService fileService;

    private static final Logger LOGGER = Logger.getLogger(QuartoResource.class.getName());

    @POST
    @RolesAllowed({ "Admin" })
    public Response insert(@Valid QuartoDTO dto) {
        LOGGER.info("Iniciando inserção de novo quarto");
        Response response = Response.status(Response.Status.CREATED).entity(service.insert(dto)).build();
        LOGGER.info("Quarto inserido com sucesso");
        return response;
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response update(@Valid QuartoDTO dto, @PathParam("id") Long id) {
        LOGGER.info("Iniciando atualização do quarto com ID: " + id);
        service.update(dto, id);
        LOGGER.info("Quarto com ID: " + id + " atualizado com sucesso");
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Iniciando exclusão do quarto com ID: " + id);
        service.delete(id);
        LOGGER.info("Quarto com ID: " + id + " excluído com sucesso");
        return Response.noContent().build();
    }

    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response findAll() {
        LOGGER.info("Buscando todos os quartos");
        Response response = Response.ok(service.findAll()).build();
        LOGGER.info("Quartos recuperados com sucesso");
        return response;
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Buscando quarto com ID: " + id);
        QuartoResponseDTO response = service.findById(id);
        LOGGER.info("Quarto com ID: " + id + " encontrado com sucesso");
        return Response.ok(response).build();
    }

    @GET
    @Path("/search/tipoQuarto/{tipoQuarto}")
    @RolesAllowed({ "User", "Admin" })
    public Response findByTipo(@PathParam("tipoQuarto") TipoQuarto tipoQuarto) {
        LOGGER.info("Buscando quartos do tipo: " + tipoQuarto);
        Response response = Response.ok(service.findByTipo(tipoQuarto)).build();
        LOGGER.info("Quartos do tipo: " + tipoQuarto + " recuperados com sucesso");
        return response;
    }

    @PATCH
    @Path("{id}/upload/imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed({ "Admin" })
    public Response salvarImagemQuarto(@PathParam("id") Long id, @MultipartForm QuartoImageForm form) {
        LOGGER.info("Iniciando upload de imagem para o quarto com ID: " + id);
        try {
            String nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());
            QuartoResponseDTO response = service.updateNomeImagem(id, nomeImagem);

            if (response == null) {
                LOGGER.warn("Quarto com ID: " + id + " não encontrado.");
                return Response.status(Status.NOT_FOUND).entity("Quarto não encontrado").build();
            }

            LOGGER.info("Imagem atualizada com sucesso para o quarto com ID: " + id);
            return Response.ok(response).build();
        } catch (IOException e) {
            LOGGER.error("Erro ao salvar imagem: " + e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/download/imagem/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @RolesAllowed({ "Admin" })
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        LOGGER.info("Iniciando download da imagem: " + nomeImagem);
        File file = fileService.obter(nomeImagem);
        if (file == null || !file.exists()) {
            LOGGER.warn("Imagem: " + nomeImagem + " não encontrada");
            return Response.status(Status.NOT_FOUND).entity("Imagem não encontrada.").build();
        }

        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition", "attachment;filename=\"" + file.getName() + "\"");
        LOGGER.info("Download da imagem: " + nomeImagem + " realizado com sucesso");
        return response.build();
    }

}
