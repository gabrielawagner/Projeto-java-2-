package org.serratec.residencia.projetofinal.dto;

import org.serratec.residencia.projetofinal.model.Carrinho;
import org.serratec.residencia.projetofinal.model.Categoria;
import org.serratec.residencia.projetofinal.model.Funcionario;
import org.serratec.residencia.projetofinal.model.Pedido;
import org.serratec.residencia.projetofinal.model.Produto;

public class DtoCarrinho {

	private Integer quantidadeProduto;

	private DtoProduto produto;

	public DtoCarrinho(Integer quantidade, DtoProduto produto) {
		this.produto = produto;
		this.quantidadeProduto = quantidade;
	}

	public DtoCarrinho() {
	}

	public Produto toPedidoProduto(Categoria categoria, Funcionario funcionario) {
		Produto produto = new Produto();

		produto.setCategoria(categoria);
		produto.setFuncionario(funcionario);

		return produto;
	}

	public Carrinho toPedidoProduto(Pedido pedido, Produto produto) {

		Carrinho pp = new Carrinho();
		pp.setPedido(pedido);
		pp.setProduto(produto);
		return pp;
	}

	public Integer getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public void setQuantidadeProduto(Integer quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}

	public DtoProduto getProduto() {
		return produto;
	}

	public void setProduto(DtoProduto produto) {
		this.produto = produto;
	}

}