package com.meu.sistema_pedidos_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meu.sistema_pedidos_springboot.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
