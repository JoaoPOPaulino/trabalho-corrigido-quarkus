package br.unitins.topicos1.service.quarto;

import java.util.List;

import br.unitins.topicos1.dto.quarto.QuartoDTO;
import br.unitins.topicos1.dto.quarto.QuartoResponseDTO;
import br.unitins.topicos1.model.TipoQuarto;
import jakarta.validation.Valid;

public interface QuartoService {
    QuartoResponseDTO insert(@Valid QuartoDTO dto);

    QuartoResponseDTO update(@Valid QuartoDTO dto, Long id);

    void delete(Long id);

    QuartoResponseDTO findById(Long id);

    List<QuartoResponseDTO> findAll();

    List<QuartoResponseDTO> findByTipo(TipoQuarto tipoQuarto);

    QuartoResponseDTO updateNomeImagem(Long id, String nomeImagem);
}
