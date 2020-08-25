package org.serratec.residencia.projetofinal.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.residencia.projetofinal.exception.CategoriaNotFoundException;
import org.serratec.residencia.projetofinal.exception.ParametroObrigatorioException;
import org.serratec.residencia.projetofinal.form.FormCategoria;
import org.serratec.residencia.projetofinal.model.Categoria;
import org.serratec.residencia.projetofinal.service.CategoriaService;
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
@RequestMapping(("/categoria"))
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@PostMapping
	public ResponseEntity<FormCategoria> inserir(@Valid @RequestBody FormCategoria formCategoria) {
		categoriaService.inserir(formCategoria);
		return new ResponseEntity<FormCategoria>(formCategoria, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listarTodos() {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<List<Categoria>>(categoriaService.listarTodos(),headers,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> listarPorId(@PathVariable Integer id) throws CategoriaNotFoundException {
		Categoria categoria = categoriaService.listarPorId(id);
		
		if(categoria != null) {
			return ResponseEntity.ok(categoria);
		}
		return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FormCategoria> substituir(@PathVariable Integer id, @RequestBody FormCategoria categoria) throws ParametroObrigatorioException, CategoriaNotFoundException {
		categoriaService.substituir(id, categoria);
		return new ResponseEntity<FormCategoria>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Integer id) throws CategoriaNotFoundException {
		categoriaService.deletar(id);
	}
}
