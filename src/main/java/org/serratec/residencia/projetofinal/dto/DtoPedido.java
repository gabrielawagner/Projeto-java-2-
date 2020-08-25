package org.serratec.residencia.projetofinal.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.residencia.projetofinal.model.Cliente;
import org.serratec.residencia.projetofinal.model.Pedido;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "codigoDoPedido","dataDePedido", "cliente", "nomeCliente", "carrinho"})
public class DtoPedido {
	
	private Integer codigoDoPedido;

	private LocalDate dataDePedido;
	
	private List<DtoCarrinho> carrinho;

	private Cliente cliente;

	public DtoPedido() {
	}
	
	public DtoPedido(Pedido pedido, List<DtoCarrinho> listaDtoCarrinho) {
		this.codigoDoPedido = pedido.getId();
		this.carrinho = listaDtoCarrinho;
		this.dataDePedido = pedido.getDataDePedido();
		this.cliente = pedido.getCliente();
	}

	public Integer getCodigoDoPedido() {
		return codigoDoPedido;
	}

	public void setCodigoDoPedido(Integer codigoDoPedido) {
		this.codigoDoPedido = codigoDoPedido;
	}

	public LocalDate getDataDePedido() {
		return dataDePedido;
	}

	public void setDataDePedido(LocalDate dataDePedido) {
		this.dataDePedido = dataDePedido;
	}

	public List<DtoCarrinho> getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(List<DtoCarrinho> carrinho) {
		this.carrinho = carrinho;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
