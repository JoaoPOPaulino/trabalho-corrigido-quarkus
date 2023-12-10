package br.unitins.topicos1.model;

import java.time.LocalDate;

import br.unitins.topicos1.dto.reserva.ReservaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reserva extends DefaultEntity {

    private LocalDate dataInicio;
    private LocalDate dataFim;

    @ManyToOne
    @JoinColumn(name = "id_quarto")
    private Quarto quarto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private Double preco;

    public Reserva() {
    }

    public Reserva(ReservaDTO dto, Quarto quarto, Usuario usuario) {
        this.dataInicio = dto.dataInicio();
        this.dataFim = dto.dataFim();
        this.preco = quarto.getPreco();
        this.quarto = quarto;
        this.usuario = usuario;
    }

    public void atualizarComDTO(ReservaDTO dto, Quarto quarto, Usuario usuario) {
        this.dataInicio = dto.dataInicio();
        this.dataFim = dto.dataFim();
        this.preco = quarto.getPreco();
        this.quarto = quarto;
        this.usuario = usuario;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean temPagamento() {
        return false;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
