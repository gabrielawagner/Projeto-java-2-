package org.serratec.residencia.projetofinal.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "PEDIDO")
@JsonPropertyOrder({"id","dataDePedido","data","cliente","carrinho"})
public class Pedido implements Comparable<Pedido> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "data_de_pedido", nullable = false)
	private LocalDate dataDePedido = LocalDate.now();

	public LocalDate getDataDePedido() {
		return dataDePedido;
	}
	
	@OnDelete(action = OnDeleteAction.CASCADE) 
	@OneToMany(targetEntity=Carrinho.class, 
	           mappedBy="pedido",
	           cascade = CascadeType.ALL)
	private List<Carrinho> carrinho;

	@ManyToOne
	@JoinColumn(name="cliente_id", 
    referencedColumnName = "id")
	private Cliente cliente;

	
	public List<Carrinho> getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(List<Carrinho> pedidoProduto) {
		this.carrinho = pedidoProduto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDataDePedido(LocalDate dataDePedido) {
		this.dataDePedido = dataDePedido;
	}

	@Override
	public int compareTo(Pedido pedidoProduto) {
		if (this.id > pedidoProduto.id) {
			return 1;
		} else if (this.id < pedidoProduto.id) {
			return -1;
		}
		return 0;
	}

}
