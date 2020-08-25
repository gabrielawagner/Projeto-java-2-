package org.serratec.residencia.projetofinal.form;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import org.serratec.residencia.projetofinal.model.Categoria;
import org.serratec.residencia.projetofinal.model.Funcionario;
import org.serratec.residencia.projetofinal.model.Produto;

public class FormProduto {
	@NotNull
	private String nome;

	@NotNull
	private String descricao;

	@NotNull
	@Past
	private LocalDate dataDeFabricacao;

	@NotNull
	@Positive
	private Integer quantidadeEmEstoque;

	@NotNull
	@Positive
	private Double valorUnitario;

	@NotNull
	private String autor;
	
	@NotNull
	Integer idFuncionario;

	@NotNull
	Integer idCategoria;

	public FormProduto() {
	}

	public FormProduto(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.dataDeFabricacao = produto.getDataDeFabricacao();
		this.quantidadeEmEstoque = produto.getQuantidadeEmEstoque();
		this.valorUnitario = produto.getValorUnitario();
		this.idCategoria = produto.getCategoria().getId();
		this.idFuncionario = produto.getFuncionario().getId();
		this.autor = produto.getAutor();
	}

	public Produto toProduto(Categoria categoria, Funcionario funcionario) {
		Produto produto = new Produto();
		produto.setNome(this.nome);
		produto.setAutor(this.autor);
		produto.setDescricao(this.descricao);
		produto.setDataDeFabricacao(this.dataDeFabricacao);
		produto.setQuantidadeEmEstoque(this.quantidadeEmEstoque);
		produto.setValorUnitario(this.valorUnitario);
		produto.setCategoria(categoria);
		produto.setFuncionario(funcionario);
		return produto;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}
	

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
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

	public LocalDate getDataDeFabricacao() {
		return dataDeFabricacao;
	}

	public void setDataDeFabricacao(LocalDate dataDeFabricacao) {
		this.dataDeFabricacao = dataDeFabricacao;
	}

	public Integer getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

}