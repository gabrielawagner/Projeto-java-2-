package org.serratec.residencia.projetofinal.dto;

import java.time.LocalDate;

import org.serratec.residencia.projetofinal.model.Categoria;
import org.serratec.residencia.projetofinal.model.Produto;

public class DtoProduto {
	

private Integer codigoDoProduto;
private String nomeDoLivro;
private String autor;
private Categoria categoria;
private LocalDate dataDePublicacao;
private String descricao;
private Double preco;

	public DtoProduto(Produto prod) {
		this.codigoDoProduto = prod.getId();
		this.nomeDoLivro = prod.getNome();
		this.autor = prod.getAutor();
		this.categoria = prod.getCategoria();
		this.dataDePublicacao = prod.getDataDeFabricacao();
		this.descricao = prod.getDescricao();
		this.preco = prod.getValorUnitario();
	}

	public Integer getCodigoDoProduto() {
		return codigoDoProduto;
	}

	public void setCodigoDoProduto(Integer codigoDoProduto) {
		this.codigoDoProduto = codigoDoProduto;
	}

	public String getNomeDoLivro() {
		return nomeDoLivro;
	}

	public void setNomeDoLivro(String nomeDoLivro) {
		this.nomeDoLivro = nomeDoLivro;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public LocalDate getDataDePublicacao() {
		return dataDePublicacao;
	}

	public void setDataDePublicacao(LocalDate dataDePublicacao) {
		this.dataDePublicacao = dataDePublicacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
}
