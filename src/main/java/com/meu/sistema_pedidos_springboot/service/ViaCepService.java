package com.meu.sistema_pedidos_springboot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.meu.sistema_pedidos_springboot.dto.EnderecoViaCepDTO;

@Service
public class ViaCepService {
	private RestClient restClient;
	
	public ViaCepService() {
		this.restClient = RestClient.create("https://viacep.com.br/ws/");
	}
	
	public EnderecoViaCepDTO buscarEnderecoPorCep(String cep) {
		return restClient.get().uri("{cep}/json/", cep) 
	            .retrieve() 
	            .body(EnderecoViaCepDTO.class);
	}
	
}
