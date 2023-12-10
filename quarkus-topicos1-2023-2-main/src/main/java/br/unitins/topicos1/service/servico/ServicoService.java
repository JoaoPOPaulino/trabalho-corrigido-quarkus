package br.unitins.topicos1.service.servico;

import java.util.List;

import br.unitins.topicos1.dto.servico.ServicoDTO;
import br.unitins.topicos1.dto.servico.ServicoResponseDTO;
import jakarta.validation.Valid;

public interface ServicoService {
    ServicoResponseDTO insert(@Valid ServicoDTO dto);

    ServicoResponseDTO update(@Valid ServicoDTO dto, Long id);

    void delete(Long id);

    ServicoResponseDTO findById(Long id);

    List<ServicoResponseDTO> findByNome(String nome);

    List<ServicoResponseDTO> findAll();
}
