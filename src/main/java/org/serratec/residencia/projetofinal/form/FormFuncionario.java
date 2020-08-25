package org.serratec.residencia.projetofinal.form;

import javax.validation.constraints.NotNull;

import org.serratec.residencia.projetofinal.model.Funcionario;

public class FormFuncionario {
	@NotNull
	private String nome;

	@NotNull
	private String cpf;

	public FormFuncionario() {
	}

	public FormFuncionario(Funcionario funcionario) {
		this.nome = funcionario.getNome();
		this.cpf = funcionario.getCpf();
	}

	public Funcionario toFuncionario(Integer id) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		funcionario.setNome(this.nome);
		funcionario.setCpf(this.cpf);

		return funcionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
