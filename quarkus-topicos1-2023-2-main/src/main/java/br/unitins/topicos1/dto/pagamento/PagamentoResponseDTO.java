package br.unitins.topicos1.dto.pagamento;

import java.time.LocalDateTime;

import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.TipoPagamento;

public record PagamentoResponseDTO(
        Long id,
        LocalDateTime dataPagamento,
        Long idReserva,
        TipoPagamento tipoPagamento,
        Double valor) {

    public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getDataPagamento(),
                pagamento.getReserva().getId(),
                pagamento.getTipoPagamento(),
                pagamento.getValor());
    }

}
