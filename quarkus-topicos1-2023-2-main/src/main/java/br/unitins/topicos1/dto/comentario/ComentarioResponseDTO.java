package br.unitins.topicos1.dto.comentario;

import java.time.LocalDateTime;

import br.unitins.topicos1.model.Comentario;

public record ComentarioResponseDTO(
        Long id,
        String conteudo,
        LocalDateTime dataCriacao,
        Long idUsuario) {

    public static ComentarioResponseDTO valueOf(Comentario comentario) {
        return new ComentarioResponseDTO(
                comentario.getId(),
                comentario.getConteudo(),
                comentario.getDataCriacao(),
                comentario.getUsuario() != null ? comentario.getUsuario().getId() : null);
    }
}