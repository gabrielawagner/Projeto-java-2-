package org.serratec.residencia.projetofinal.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.serratec.residencia.projetofinal.exception.FuncionarioNotFoundException;
import org.serratec.residencia.projetofinal.exception.ParametroObrigatorioException;
import org.serratec.residencia.projetofinal.form.FormFuncionario;
import org.serratec.residencia.projetofinal.model.Funcionario;
import org.serratec.residencia.projetofinal.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

	@Autowired
	FuncionarioRepository funcionarioRepository;

	public Funcionario inserir(FormFuncionario formFuncionario) {
		return funcionarioRepository.save(formFuncionario.toFuncionario(null));
	}

	public List<Funcionario> listarTodos() {
		return funcionarioRepository.findAll();
	}

	public Funcionario listarPorId(Integer id) throws FuncionarioNotFoundException {
		Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
		
		if (optionalFuncionario.isPresent())
			return optionalFuncionario.get();
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		List<FormFuncionario> formFuncionario= new ArrayList<FormFuncionario>();
		Collections.sort(funcionarios);
		for (Funcionario funcionario : funcionarios) {
			formFuncionario.add(new FormFuncionario(funcionario));
		}
		
		throw new FuncionarioNotFoundException("Funcionario com o id: " + id + " não encontrada.");
	}

	public void deletar(Integer id) throws FuncionarioNotFoundException {
		Funcionario funcionarioDeletada = listarPorId(id);
		funcionarioRepository.delete(funcionarioDeletada);
	}

	public Funcionario substituir(Integer id, FormFuncionario formFuncionario ) throws ParametroObrigatorioException, FuncionarioNotFoundException {
		if (formFuncionario == null)
			throw new ParametroObrigatorioException("Funcionario 'Funcionario' é obrigatória");

		Funcionario funcionarioNoBanco = listarPorId(id);

		if (formFuncionario.getNome() != null) {
			funcionarioNoBanco.setNome(formFuncionario.getNome());
		}

		if (formFuncionario.getCpf() != null) {
			funcionarioNoBanco.setCpf(formFuncionario.getCpf());
		}

		return funcionarioRepository.save(funcionarioNoBanco);
	}

}
