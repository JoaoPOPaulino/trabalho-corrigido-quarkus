package br.unitins.topicos1.dto.servico;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public record ServicoDTO(
                @NotNull(message = "O nome não pode ser nulo.") String nome,
                @NotNull(message = "A descrição não pode ser nula") String descricao,
                @NotNull(message = "A hora inicial não pode ser nula.") LocalTime horaI,
                @NotNull(message = "A hora final não pode ser nula.") LocalTime horaF

) {

}
