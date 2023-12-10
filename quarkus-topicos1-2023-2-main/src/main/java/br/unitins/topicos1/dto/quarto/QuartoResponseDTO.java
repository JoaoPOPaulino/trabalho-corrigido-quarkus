package br.unitins.topicos1.dto.quarto;

import br.unitins.topicos1.model.Quarto;
import br.unitins.topicos1.model.TipoQuarto;

public record QuartoResponseDTO(
        Long id,
        TipoQuarto tipoQuarto,
        int numero,
        double preco,
        boolean disponivel,
        String nomeImagem) {

    public static QuartoResponseDTO valueOf(Quarto quarto) {
        return new QuartoResponseDTO(
                quarto.getId(),
                quarto.getTipoQuarto(),
                quarto.getNumero(),
                quarto.getPreco(),
                quarto.isDisponivel(),
                quarto.getNomeImagem());
    }

}
