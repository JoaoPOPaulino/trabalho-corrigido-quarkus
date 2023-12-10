package br.unitins.topicos1.dto.reserva;

import java.time.LocalDate;

import br.unitins.topicos1.model.Reserva;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record ReservaDTO(
                @FutureOrPresent(message = "A data de início da reserva deve ser igual ou posterior à data atual") @NotNull(message = "A data de início não pode ser nula.") LocalDate dataInicio,
                @FutureOrPresent(message = "A data do final da reserva deve ser igual ou posterior à data atual") @NotNull(message = "A data de fim não pode ser nula.") LocalDate dataFim,
                @NotNull(message = "O idQuarto não pode ser nulo.") Long idQuarto,
                @NotNull(message = "O idUsuario não pode ser nulo.") Long idUsuario) {
        public static ReservaDTO valueOf(Reserva reserva) {
                return new ReservaDTO(
                                reserva.getDataInicio(),
                                reserva.getDataFim(),
                                reserva.getQuarto().getId(),
                                reserva.getUsuario().getId());
        }
}
