package org.serratec.residencia.projetofinal.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUTO")
public class Produto implements Comparable<Produto> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "autor", nullable = false)
	private String autor;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Column(name = "data_de_fabricacao", nullable = false)
	private LocalDate dataDeFabricacao;

	@Column(name = "quantidade_em_estoque", nullable = false)
	private Integer quantidadeEmEstoque;

	@Column(name = "valor_unitario", nullable = false)
	private Double valorUnitario;

	@ManyToOne
	@JoinColumn(name="funcionario_id", 
            referencedColumnName = "id")
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name="categoria_id", 
    referencedColumnName = "id")
	private Categoria categoria;

	@OneToMany(targetEntity=Carrinho.class, 
	           mappedBy="produto")
	private List<Carrinho> carrinho;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

	public void setDataDeFabricacao(LocalDate date) {
		this.dataDeFabricacao = date;
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

	public void setCarrinho(List<Carrinho> pedidoProduto) {
		this.carrinho = pedidoProduto;
	}

	@Override
	public int compareTo(Produto produto) {
		if (this.id > produto.id) {
			return 1;
		} else if (this.id < produto.id) {
			return -1;
		}
		return 0;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

}
