package org.serratec.residencia.projetofinal.repository;

import java.util.ArrayList;
import java.util.List;

import org.serratec.residencia.projetofinal.model.Cliente;
import org.serratec.residencia.projetofinal.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	

	ArrayList<Pedido> findByClienteCpf(String cpf);

	List<Pedido> findByCliente(Cliente cliente);
	
}
