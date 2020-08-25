package org.serratec.residencia.projetofinal.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.residencia.projetofinal.dto.DtoProduto;
import org.serratec.residencia.projetofinal.exception.ErroNoCadastroException;
import org.serratec.residencia.projetofinal.exception.ParametroObrigatorioException;
import org.serratec.residencia.projetofinal.exception.ProdutoNotFoundException;
import org.serratec.residencia.projetofinal.form.FormProduto;
import org.serratec.residencia.projetofinal.model.Produto;
import org.serratec.residencia.projetofinal.service.ProdutoService;
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
@RequestMapping("/produto")

public class ProdutoController {
	
	@Autowired
    private ProdutoService produtoService;
    
	@PostMapping
    public ResponseEntity<Void> inserir(@Valid @RequestBody FormProduto produto) throws ErroNoCadastroException {
        produtoService.inserir(produto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
		
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<List<Produto>>(produtoService.listarTodos(),headers,HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produto> listarPorId(@PathVariable Integer id) throws ProdutoNotFoundException {
    	Produto produto = produtoService.listarPorId(id);
    	
    	
        if(produto != null) {
            return ResponseEntity.ok(produto);
        }
        
        return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/buscaPorNome")
    public DtoProduto buscaPorNome(@RequestBody String nome) throws ProdutoNotFoundException {
    	Produto produto = produtoService.buscaPorNome(nome);
    	return new DtoProduto(produto);
    }
    
    @GetMapping("/categoria/{categoria}")
    public List<Produto> buscaPorCategoria(@PathVariable String categoria) {
    	return produtoService.buscaPorCategoria(categoria);
    }
   
    @PutMapping("/{id}")
    public ResponseEntity<Void> substituir(@PathVariable Integer id, @RequestBody FormProduto formProduto) throws ParametroObrigatorioException, ProdutoNotFoundException {
    	produtoService.substituir(id, formProduto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) throws ProdutoNotFoundException {
        produtoService.deletar(id);
    }

}
