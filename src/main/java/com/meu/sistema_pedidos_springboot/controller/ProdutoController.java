package com.meu.sistema_pedidos_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.meu.sistema_pedidos_springboot.dto.ProdutoRequestDTO;
import com.meu.sistema_pedidos_springboot.model.Produto;
import com.meu.sistema_pedidos_springboot.repository.ProdutoRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") 
@RestController
@RequestMapping("/produtos")
@Tag(name = "📦 Produtos", description = "Operações para gerenciamento do estoque")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	@Operation(
			summary = "Lista todos os produtos", 
			description = "Retorna uma lista completa de produtos cadastrados no banco."
		)
	public List<Produto> buscarTodas(){
		return produtoRepository.findAll();
	}
	@Operation(
	        summary = "Busca um produto pelo ID", 
	        description = "Retorna os detalhes de um único produto com base no número de identificação fornecido na URL."
	    )
	@GetMapping("/{id}")
	public Produto buscarPorId(@PathVariable Integer id) {
		return produtoRepository.findById(id).orElse(null);
	}
	@Operation(
	        summary = "Cadastra um novo produto", 
	        description = "Recebe os dados de um produto no corpo da requisição (JSON) e o salva no estoque do banco de dados."
	    )
	@PostMapping
	public Produto salvar(@RequestBody @Valid ProdutoRequestDTO dto) {
		Produto produto = new Produto();
		produto.setNome(dto.nome());
		return produtoRepository.save(produto);
	}
	@Operation(
	        summary = "Atualiza um produto existente", 
	        description = "Substitui os dados de um produto cadastrado pelo novo objeto enviado no corpo da requisição."
	    )
	@PutMapping("/{id}")
	public Produto atualizar(@PathVariable Integer id, @RequestBody Produto produtoAtualizado) {
		Produto produtoExistente = produtoRepository.findById(id).orElse(null);
		
		if(produtoExistente != null) {
			produtoExistente.setNome(produtoAtualizado.getNome());
			
			return produtoRepository.save(produtoExistente);
		}
		
		
		return null;
	}
	@Operation(
	        summary = "Remove um produto do estoque", 
	        description = "Deleta permanentemente o produto do banco de dados com base no ID fornecido."
	    )
	@DeleteMapping("/{id}")
	public String apagar(@PathVariable Integer id) {
		produtoRepository.deleteById(id);
		return "Cadastro apagado";
	}
}
