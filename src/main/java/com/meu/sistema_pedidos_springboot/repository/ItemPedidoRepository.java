package com.meu.sistema_pedidos_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meu.sistema_pedidos_springboot.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{

}
