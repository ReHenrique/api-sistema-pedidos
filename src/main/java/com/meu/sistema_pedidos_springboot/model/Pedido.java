package com.meu.sistema_pedidos_springboot.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_pedido")
	private Integer idPedido;
	
	private LocalDate data;
	
	@ManyToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;
	
	public Pedido() {
		
	}
	
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalDate getData() {
		return data;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	
}
