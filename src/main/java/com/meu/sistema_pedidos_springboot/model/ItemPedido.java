package com.meu.sistema_pedidos_springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Item_Pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_item_pedido")
	private Integer idItemPedido;
	private Integer quantidade;
	
	@ManyToOne
	@JoinColumn(name="id_pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;
	
	public ItemPedido() {
		
	}

	public Integer getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
	
}
