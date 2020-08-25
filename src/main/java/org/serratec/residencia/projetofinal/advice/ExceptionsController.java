package org.serratec.residencia.projetofinal.advice;

import org.serratec.residencia.projetofinal.exception.CategoriaNotFoundException;
import org.serratec.residencia.projetofinal.exception.ClienteNotFoundException;
import org.serratec.residencia.projetofinal.exception.ErroNoCadastroException;
import org.serratec.residencia.projetofinal.exception.FuncionarioNotFoundException;
import org.serratec.residencia.projetofinal.exception.ParametroObrigatorioException;
import org.serratec.residencia.projetofinal.exception.PedidoNotFoundException;
import org.serratec.residencia.projetofinal.exception.ProdutoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsController {

	@ExceptionHandler(ErroNoCadastroException.class)
	public ResponseEntity<Void> trataTodoNotFound(ErroNoCadastroException exception) {
		String mensagem = exception.getMessage();
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).header("x-erro-msg", mensagem).build();
	}
	
	@ExceptionHandler(CategoriaNotFoundException.class)
	public ResponseEntity<Void> trataTodoNotFound(CategoriaNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();
	}
	
	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity<Void> trataTodoNotFound(ClienteNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();
	}
	
	@ExceptionHandler(FuncionarioNotFoundException.class)
	public ResponseEntity<Void> trataTodoNotFound(FuncionarioNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();
	}
	
	@ExceptionHandler(ParametroObrigatorioException.class)
	public ResponseEntity<Void> trataTodoNotFound(ParametroObrigatorioException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();
	}
	
	@ExceptionHandler(PedidoNotFoundException.class)
	public ResponseEntity<Void> trataTodoNotFound(PedidoNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();
	}
	
	@ExceptionHandler(ProdutoNotFoundException.class)
	public ResponseEntity<Void> trataTodoNotFound(ProdutoNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();
	}
	
}