package br.unitins.topicos1.service.reserva;

import java.util.List;

import br.unitins.topicos1.dto.reserva.ReservaDTO;
import br.unitins.topicos1.dto.reserva.ReservaResponseDTO;
import jakarta.validation.Valid;

public interface ReservaService {

    ReservaResponseDTO insert(@Valid ReservaDTO dto);

    ReservaResponseDTO update(@Valid ReservaDTO dto, Long id);

    void delete(Long id);

    ReservaResponseDTO findById(Long id);

    List<ReservaResponseDTO> findByAll();

    List<ReservaResponseDTO> findReservaByUsuarioId(Long usuarioId);

}
