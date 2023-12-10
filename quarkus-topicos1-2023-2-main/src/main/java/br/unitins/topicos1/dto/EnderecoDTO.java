package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Endereco;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        @NotNull(message = "O campo não pode ser nulo.") String estado,
        @NotNull(message = "O campo não pode ser nulo.") String cidade,
        @NotNull(message = "O campo não pode ser nulo.") String quadra,
        @NotNull(message = "O campo não pode ser nulo.") String rua,
        @NotNull(message = "O campo não pode ser nulo.") Integer numero) {
    public static EnderecoDTO valueOf(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getQuadra(),
                endereco.getRua(),
                endereco.getNumero());
    }

}
