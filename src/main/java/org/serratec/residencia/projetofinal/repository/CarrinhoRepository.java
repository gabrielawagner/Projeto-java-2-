package org.serratec.residencia.projetofinal.repository;

import java.util.List;

import org.serratec.residencia.projetofinal.model.Carrinho;
import org.serratec.residencia.projetofinal.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer>{

	List<Carrinho> findAllByPedido(Pedido pedido);
	
	List<Integer> findQuantidadesByPedido(Pedido pedido);

	List<Carrinho> findByPedido(Pedido p);

	void deleteByProdutoId(Carrinho car);

}
