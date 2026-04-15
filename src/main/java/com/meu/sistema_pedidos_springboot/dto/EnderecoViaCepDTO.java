package com.meu.sistema_pedidos_springboot.dto;

public record EnderecoViaCepDTO(String cep,
	    String logradouro,
	    String complemento,
	    String bairro,
	    String localidade, 
	    String uf
	) {}