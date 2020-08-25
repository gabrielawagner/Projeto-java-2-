package org.serratec.residencia.projetofinal.controller;

import java.util.List;

import javax.validation.Valid;

import org.serratec.residencia.projetofinal.dto.DtoPedido;
import org.serratec.residencia.projetofinal.exception.ClienteNotFoundException;
import org.serratec.residencia.projetofinal.exception.PedidoNotFoundException;
import org.serratec.residencia.projetofinal.exception.ProdutoNotFoundException;
import org.serratec.residencia.projetofinal.form.FormPedido;
import org.serratec.residencia.projetofinal.model.Pedido;
import org.serratec.residencia.projetofinal.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	 private PedidoService pedidoService;
    
	@PostMapping
    public ResponseEntity<FormPedido> inserir(@Valid @RequestBody FormPedido formPedido) throws ClienteNotFoundException, ProdutoNotFoundException {
		pedidoService.inserir(formPedido);
        return new ResponseEntity<FormPedido>(HttpStatus.CREATED);
    }
		
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<List<Pedido>>(pedidoService.listarTodos(),headers,HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DtoPedido> listarPorId(@PathVariable Integer id) throws PedidoNotFoundException, ClienteNotFoundException {
    	DtoPedido pedido = pedidoService.listarPorId(id);
       
        if(pedido != null) {
        	
            return ResponseEntity.ok(pedido);
        }
        
        return new ResponseEntity<DtoPedido>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) throws PedidoNotFoundException, ClienteNotFoundException {
    	pedidoService.deletar(id);
    }

}
