package com.meu.sistema_pedidos_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meu.sistema_pedidos_springboot.dto.PedidoRequestDTO;
import com.meu.sistema_pedidos_springboot.model.Pedido;
import com.meu.sistema_pedidos_springboot.model.Pessoa;
import com.meu.sistema_pedidos_springboot.repository.PedidoRepository;
import com.meu.sistema_pedidos_springboot.repository.PessoaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.*;
@CrossOrigin(origins = "http://localhost:4200") 
@RestController
@RequestMapping ("/pedidos")
@Tag(name="Pedidos", description="Operações para gerenciamento de pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired 
	private PessoaRepository pessoaRepository;
	
	
	@GetMapping
	@Operation(
			summary="Lista todos os pedidos",
			description="Retorna uma lista completa de pedidos cadastrados no banco."
	)
	public List<Pedido> buscarTodos() {
		return pedidoRepository.findAll();
	}
	@GetMapping("/{id}")
	@Operation(
			summary="Busca um pedido pelo ID",
			description="Retorna os detalhes de um único pedido com base no número de identificação fornecido na URL."
	)
	public Pedido buscarPorId(@PathVariable Integer id) {
		return pedidoRepository.findById(id).orElse(null);
	}
	@PostMapping
	@Operation(
			summary="Cadastra um novo pedido",
			description="Recebe os dados de um pedido no corpo da requisição (JSON) e o salva no estoque do banco de dados."
	)
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
	@Operation(
			summary="Atualiza um pedido existente",
			description="Substitui os dados de um pedido cadastrado pelo novo objeto enviado no corpo da requisição."
	)
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
	@Operation(
			summary="Remove um pedido do estoque",
			description="Deleta permanentemente um pedido do banco de dados com base no ID fornecido."
	)
	@DeleteMapping ("/{id}")
	public String apagar(@PathVariable Integer id) {
		pedidoRepository.deleteById(id);
		return "Cadastro apagado!";
	}
}
