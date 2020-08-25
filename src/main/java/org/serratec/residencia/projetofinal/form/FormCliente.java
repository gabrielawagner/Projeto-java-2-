package org.serratec.residencia.projetofinal.form;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.serratec.residencia.projetofinal.model.Cliente;
import org.serratec.residencia.projetofinal.model.Pedido;

public class FormCliente {
	@NotNull
	private String nome;
	@NotNull
	private String endereco;
	@NotNull
	private String telefone;
	@NotNull
	private String nomeDeUsuario;
	@NotNull
	@Email
	private String email;
	@NotNull
	@Size(min=11, max=11)
	private String cpf;
	@NotNull
	private LocalDate dataNascimento;

	public FormCliente() {
	}

	public FormCliente(Cliente cliente) {
		this.nome = cliente.getNome();
		this.endereco = cliente.getEndereco();
		this.telefone = cliente.getTelefone();
		this.nomeDeUsuario = cliente.getNomedeusuario();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
		this.dataNascimento = cliente.getDataNascimento();
	}

	public Cliente toCliente(List<Pedido> Pedidos) {
		Cliente cliente = new Cliente();
		cliente.setNome(this.nome);
		cliente.setEndereco(this.endereco);
		cliente.setTelefone(this.telefone);
		cliente.setNomedeusuario(this.nomeDeUsuario);
		cliente.setEmail(this.email);
		cliente.setCpf(this.cpf);
		cliente.setDataNascimento(this.dataNascimento);

		return cliente;
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

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
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

}
