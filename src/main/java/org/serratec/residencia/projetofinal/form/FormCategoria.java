package org.serratec.residencia.projetofinal.form;

import javax.validation.constraints.NotNull;

import org.serratec.residencia.projetofinal.model.Categoria;

public class FormCategoria {
	@NotNull
	private String nome;
	
	@NotNull
	private String descricao;

	public FormCategoria() {
	}

	public FormCategoria(Categoria categoria) {
		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
	}
	
	public Categoria toCategoria(Integer id) {
		Categoria categoria = new Categoria();
		categoria.setId(id);
		categoria.setNome(this.nome);
		categoria.setDescricao(this.descricao);

		return categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
