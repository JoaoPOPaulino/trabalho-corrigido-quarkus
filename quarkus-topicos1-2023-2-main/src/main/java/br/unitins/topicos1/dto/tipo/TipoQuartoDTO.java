package br.unitins.topicos1.dto.tipo;

import br.unitins.topicos1.model.TipoQuarto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TipoQuartoDTO(
        @NotNull(message = "O ID do tipo de quarto não pode ser nulo.") @Min(value = 1, message = "O ID do tipo de quarto deve ser positivo.") Integer id,

        @NotBlank(message = "A descrição do tipo de quarto não pode ser vazia.") String label) {

    public static TipoQuartoDTO valueOf(TipoQuarto tipoQuarto) {
        return new TipoQuartoDTO(tipoQuarto.getId(), tipoQuarto.getLabel());
    }

}