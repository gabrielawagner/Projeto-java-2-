package org.serratec.residencia.projetofinal.controller;

import java.util.List;

import javax.validation.Valid;

import org.postgresql.util.PSQLException;
import org.serratec.residencia.projetofinal.exception.ClienteNotFoundException;
import org.serratec.residencia.projetofinal.exception.ErroNoCadastroException;
import org.serratec.residencia.projetofinal.exception.ParametroObrigatorioException;
import org.serratec.residencia.projetofinal.form.FormCliente;
import org.serratec.residencia.projetofinal.model.Cliente;
import org.serratec.residencia.projetofinal.service.ClienteService;
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
@RequestMapping("/Cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@PostMapping
	public ResponseEntity<FormCliente> inserir(@RequestBody @Valid FormCliente formCliente) throws ErroNoCadastroException, PSQLException {
		clienteService.inserir(formCliente);
		return new ResponseEntity<FormCliente>(formCliente, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> listarTodos() {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<List<Cliente>>(clienteService.listarTodos(), headers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarPorId(@PathVariable Integer id) throws ClienteNotFoundException {
		Cliente cliente = clienteService.listarPorId(id);
		
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		}
		return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> substituir(@PathVariable Integer id, @RequestBody FormCliente cliente)
			throws ParametroObrigatorioException, ClienteNotFoundException {
		clienteService.substituir(id, cliente);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws ClienteNotFoundException {
		clienteService.deletar(id);
	}

}