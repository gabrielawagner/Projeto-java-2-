package org.serratec.residencia.projetofinal.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente implements Comparable<Cliente>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "nome", nullable = false)
	private String nome;
	@Column(name = "endereco", nullable = false)
	private String endereco;
	@Column(name = "telefone", nullable = false)
	private String telefone;
	@Column(name = "nome_de_usuario", nullable = false, unique = true)
	private String nomeDeUsuario;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "cpf", nullable = false, unique = true, length=14)
	private String cpf;
	@Column(name = "data_de_nascimento", nullable = false)
	private LocalDate dataNascimento;
	
	@OneToMany(targetEntity = Pedido.class, mappedBy = "cliente")
	private List<Pedido> pedido;


	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNomedeusuario() {
		return nomeDeUsuario;
	}

	public void setNomedeusuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	@Override
	public int compareTo(Cliente cliente) {
		if (this.id > cliente.id) {
			return 1;
		} else if (this.id < cliente.id) {
			return -1;
		}
		return 0;
	}
}
