package br.unitins.topicos1.model;

import br.unitins.topicos1.dto.quarto.QuartoDTO;
import jakarta.persistence.Entity;

@Entity
public class Quarto extends DefaultEntity {

    private TipoQuarto tipoQuarto;
    private Integer numero;
    private Double preco;
    private boolean disponivel;
    private String nomeImagem;

    public Quarto() {

    }

    public Quarto(QuartoDTO dto) {
        this.tipoQuarto = TipoQuarto.valueOf(dto.tipoQuarto().id());
        this.numero = dto.numero();
        this.preco = dto.preco();
        this.disponivel = dto.disponivel();
    }

    public void atualizarComDTO(QuartoDTO dto) {
        this.tipoQuarto = TipoQuarto.valueOf(dto.tipoQuarto().id());
        this.numero = dto.numero();
        this.preco = dto.preco();
        this.disponivel = dto.disponivel();
    }

    public Integer getNumero() {
        return numero;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setNumero(Integer numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("O número do quarto deve ser positivo.");
        }
        this.numero = numero;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("O preço do quarto deve ser positivo.");
        }
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(TipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

}
