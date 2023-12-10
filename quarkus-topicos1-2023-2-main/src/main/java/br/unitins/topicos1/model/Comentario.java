package br.unitins.topicos1.model;

import java.time.LocalDateTime;

import br.unitins.topicos1.dto.comentario.ComentarioDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comentario extends DefaultEntity {

	private String conteudo;
	private LocalDateTime dataCriacao;

	@ManyToOne
	@JoinColumn(name = "id_usuarios")
	private Usuario usuario;

	public Comentario() {

	}

	public Comentario(ComentarioDTO dto, Usuario usuario) {
		this.conteudo = dto.conteudo();
		this.dataCriacao = dto.dataCriacao();
		this.usuario = usuario;
	}

	public void atualizarComDto(ComentarioDTO dto) {
		this.conteudo = dto.conteudo();
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}