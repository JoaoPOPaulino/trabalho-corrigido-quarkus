package br.unitins.topicos1.dto.quarto;

import br.unitins.topicos1.dto.tipo.TipoQuartoDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record QuartoDTO(
        @NotNull(message = "O número do quarto não pode ser nulo.") @Min(value = 1, message = "O número do quarto deve ser positivo.") Integer numero,

        @NotNull(message = "O preço não pode ser nulo.") @Min(value = 1, message = "O preço deve ser positivo.") Double preco,

        boolean disponivel,

        @NotNull(message = "O tipo do quarto não pode ser nulo.") TipoQuartoDTO tipoQuarto) {
}