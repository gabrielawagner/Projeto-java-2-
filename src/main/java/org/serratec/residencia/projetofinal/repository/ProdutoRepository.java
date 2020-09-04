package org.serratec.residencia.projetofinal.repository;

import java.util.List;
import java.util.Optional;

import org.serratec.residencia.projetofinal.model.Carrinho;
import org.serratec.residencia.projetofinal.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	List<Produto> findByCarrinho(Carrinho c);
	
	@Query("SELECT p FROM Produto p JOIN Carrinho c ON c.produto = p.id JOIN Pedido ped ON ped.id = c.pedido WHERE ped.id = :idPedido")
	List<Produto> buscaPedidosProdutosPorIdPedido(Integer idPedido);

	Optional<Produto> findByNome(String nome);

	List<Produto> findByCategoriaNome(String categoria);
}
