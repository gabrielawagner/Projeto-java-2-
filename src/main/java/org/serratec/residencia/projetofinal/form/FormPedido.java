package org.serratec.residencia.projetofinal.form;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.serratec.residencia.projetofinal.model.Cliente;
import org.serratec.residencia.projetofinal.model.Pedido;

public class FormPedido {

	@NotNull
	private Integer idCliente;

	@NotNull
	private List<Integer> idProdutos;

	@NotNull
	private List<Integer> quantidadesProdutos;

	public FormPedido() {
	}

	public FormPedido(Pedido pedido) {
		this.idCliente = pedido.getCliente().getId();
	}

	public FormPedido(@NotNull Integer idCliente, @NotNull List<Integer> idProduto,
			@NotNull List<Integer> quantidadesProdutos) {
		this.idCliente = idCliente;
		this.idProdutos = idProduto;
		this.quantidadesProdutos = quantidadesProdutos;
	}

	public void adicionaProdutoLista(Integer id) {
		this.idProdutos.add(id);
	}

	public List<Integer> getQuantidadesProdutos() {
		return quantidadesProdutos;
	}

	public void setQuantidadesProdutos(List<Integer> quantidadesProdutos) {
		this.quantidadesProdutos = quantidadesProdutos;
	}

	public List<Integer> getIdProdutos() {
		return idProdutos;
	}

	public void setIdProdutos(List<Integer> idProdutos) {
		this.idProdutos = idProdutos;
	}

	public Pedido toPedido(Cliente cliente) {
		Pedido pedido = new Pedido();

		pedido.setCliente(cliente);

		return pedido;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer fkCliente) {
		this.idCliente = fkCliente;
	}

}
