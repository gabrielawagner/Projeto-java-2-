package org.serratec.residencia.projetofinal.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.residencia.projetofinal.exception.CategoriaNotFoundException;
import org.serratec.residencia.projetofinal.exception.FuncionarioNotFoundException;
import org.serratec.residencia.projetofinal.exception.ParametroObrigatorioException;
import org.serratec.residencia.projetofinal.form.FormFuncionario;
import org.serratec.residencia.projetofinal.model.Funcionario;
import org.serratec.residencia.projetofinal.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funcionario")

public class FuncionarioController {
	
	@Autowired
	FuncionarioService funcionarioService;
	
	
	@PostMapping
	public ResponseEntity<Funcionario> inserir(@RequestBody @Valid FormFuncionario formFuncionario) {
		return new ResponseEntity<Funcionario>(funcionarioService.inserir(formFuncionario), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Funcionario>> listarTodos() {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<List<Funcionario>>(funcionarioService.listarTodos(),headers,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> listarPorId(@PathVariable Integer id) throws CategoriaNotFoundException, FuncionarioNotFoundException {
		Funcionario funcionario = funcionarioService.listarPorId(id);
		
		if(funcionario != null) {
			return ResponseEntity.ok(funcionario);
		}
		return new ResponseEntity<Funcionario>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> substituir(@PathVariable Integer id, @Valid @RequestBody FormFuncionario formFuncionario) throws ParametroObrigatorioException, CategoriaNotFoundException, FuncionarioNotFoundException {
		funcionarioService.substituir(id, formFuncionario);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws CategoriaNotFoundException, FuncionarioNotFoundException {
		funcionarioService.deletar(id);
	}
}
