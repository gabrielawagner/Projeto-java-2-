package org.serratec.residencia.projetofinal.repository;

import org.serratec.residencia.projetofinal.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{
}
