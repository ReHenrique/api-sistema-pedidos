package com.meu.sistema_pedidos_springboot.repository;

import com.meu.sistema_pedidos_springboot.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
}
