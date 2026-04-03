package com.meu.sistema_pedidos_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.meu.sistema_pedidos_springboot.model.Produto;
import com.meu.sistema_pedidos_springboot.repository.ProdutoRepository;

import dto.ProdutoRequestDTO;
import jakarta.validation.Valid;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200") 
@RestController
@RequestMapping("/produtos")

public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> buscarTodas(){
		return produtoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Produto buscarPorId(@PathVariable Integer id) {
		return produtoRepository.findById(id).orElse(null);
	}
	@PostMapping
	public Produto salvar(@RequestBody @Valid ProdutoRequestDTO dto) {
		Produto produto = new Produto();
		produto.setNome(dto.nome());
		return produtoRepository.save(produto);
	}
	
	@PutMapping("/{id}")
	public Produto atualizar(@PathVariable Integer id, @RequestBody Produto produtoAtualizado) {
		Produto produtoExistente = produtoRepository.findById(id).orElse(null);
		
		if(produtoExistente != null) {
			produtoExistente.setNome(produtoAtualizado.getNome());
			
			return produtoRepository.save(produtoExistente);
		}
		
		
		return null;
	}
	
	@DeleteMapping("/{id}")
	public String apagar(@PathVariable Integer id) {
		produtoRepository.deleteById(id);
		return "Cadastro apagado";
	}
}
