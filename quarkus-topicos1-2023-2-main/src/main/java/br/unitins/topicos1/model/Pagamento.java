package br.unitins.topicos1.model;

import java.time.LocalDateTime;

import br.unitins.topicos1.dto.pagamento.PagamentoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pagamento extends DefaultEntity {
    private TipoPagamento tipoPagamento;
    private LocalDateTime dataPagamento;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    public Pagamento() {

    }

    public Pagamento(PagamentoDTO dto, Reserva reserva) {
        this.tipoPagamento = TipoPagamento.valueOf(dto.tipoPagamento().id());
        this.dataPagamento = dto.dataPagamento();
        this.reserva = reserva;
        if (this.reserva != null) {
            this.valor = this.reserva.getPreco();
        }
    }

    public void atualizarComDto(PagamentoDTO dto) {
        this.tipoPagamento = TipoPagamento.valueOf(dto.tipoPagamento().id());
        this.dataPagamento = dto.dataPagamento();
        if (this.reserva != null) {
            this.valor = this.reserva.getPreco();
        }
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
