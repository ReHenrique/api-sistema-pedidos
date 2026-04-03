package com.meu.sistema_pedidos_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meu.sistema_pedidos_springboot.model.Pedido;
import com.meu.sistema_pedidos_springboot.model.Pessoa;
import com.meu.sistema_pedidos_springboot.repository.PedidoRepository;
import com.meu.sistema_pedidos_springboot.repository.PessoaRepository;

import dto.PedidoRequestDTO;
import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping ("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired 
	private PessoaRepository pessoaRepository;
	@GetMapping
	public List<Pedido> buscarTodos() {
		return pedidoRepository.findAll();
	}
	@GetMapping("/{id}")
	public Pedido buscarPorId(@PathVariable Integer id) {
		return pedidoRepository.findById(id).orElse(null);
	}
	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody @Valid PedidoRequestDTO dto) {
		Pessoa pessoa = pessoaRepository.findById(dto.pessoa().getIdPessoa()).orElse(null);
		
		if(pessoa == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Nenhuma pessoa encontrada com o ID " + dto.pessoa().getIdPessoa());
		}
		
		
		Pedido pedido= new Pedido();
		pedido.setData(dto.data());
		pedido.setPessoa(pessoa); 
		pedidoRepository.save(pedido);
		return ResponseEntity.ok(pedido);
	}
	
	@PutMapping ("/{id}")
	public Pedido atualizar(@PathVariable Integer id,@RequestBody Pedido pedidoAtualizado) {
		Pedido pedidoExistente = pedidoRepository.findById(id).orElse(null);
		if(pedidoExistente != null) {
			pedidoExistente.setData(pedidoAtualizado.getData());
			pedidoExistente.setPessoa(pedidoAtualizado.getPessoa());
			return pedidoRepository.save(pedidoExistente);
		}
		
		return null;
	}
	
	@DeleteMapping ("/{id}")
	public String apagar(@PathVariable Integer id) {
		pedidoRepository.deleteById(id);
		return "Cadastro apagado!";
	}
}
