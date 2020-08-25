package org.serratec.residencia.projetofinal.repository;

import java.util.Optional;

import org.serratec.residencia.projetofinal.model.Cliente;
import org.serratec.residencia.projetofinal.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	Optional<Cliente> findClienteByCpf(String cpf);

	Optional<Cliente> findClienteByEmail(String email);

	Optional<Cliente> findClienteByNomeDeUsuario(String nomeDeUsuario);

	Cliente findByPedido(Pedido pedido);

}
