package br.unitins.topicos1.service.comentario;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.topicos1.dto.comentario.ComentarioDTO;
import br.unitins.topicos1.dto.comentario.ComentarioResponseDTO;
import jakarta.validation.Valid;

public interface ComentarioService {
    ComentarioResponseDTO insert(@Valid ComentarioDTO dto);

    List<ComentarioResponseDTO> findAll();

    ComentarioResponseDTO findById(Long id);

    ComentarioResponseDTO update(@Valid ComentarioDTO dto, Long id);

    void delete(Long id);

    List<ComentarioResponseDTO> findComentariosByData(LocalDateTime data);
}