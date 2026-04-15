package com.meu.sistema_pedidos_springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meu.sistema_pedidos_springboot.dto.ItemPedidoRequestDTO;
import com.meu.sistema_pedidos_springboot.model.ItemPedido;
import com.meu.sistema_pedidos_springboot.model.Pedido;
import com.meu.sistema_pedidos_springboot.model.Produto;
import com.meu.sistema_pedidos_springboot.repository.ItemPedidoRepository;
import com.meu.sistema_pedidos_springboot.repository.PedidoRepository;
import com.meu.sistema_pedidos_springboot.repository.ProdutoRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:4200") 
@RestController
@RequestMapping("/item_pedidos")
@Tag(name="Itens Pedidos", description="Operações para gerenciamento de itens pedidos")
public class ItemPedidoController {

	@Autowired 
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired 
	private PedidoRepository pedidoRepository; 
	@Autowired 
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	@Operation(
			summary = "Lista todos os itens pedidos", 
			description = "Retorna uma lista completa de itens pedidos cadastrados no banco."
	)
	public List<ItemPedido> buscarTodos(){
		return itemPedidoRepository.findAll();
	}
	@GetMapping("/{id}")
	@Operation(
			 summary = "Busca um item pedido pelo ID", 
		     description = "Retorna os detalhes de um único item pedido com base no número de identificação fornecido na URL."
	)
	public ItemPedido buscarPorId(@PathVariable Integer id) {
		return itemPedidoRepository.findById(id).orElse(null);
	}
	@PostMapping
	@Operation(
			summary = "Cadastra um novo item pedido", 
	        description = "Recebe os dados de um item pedido no corpo da requisição (JSON) e o salva no estoque do banco de dados."
	)
	public ResponseEntity<Object> salvar(@RequestBody @Valid ItemPedidoRequestDTO dto) {
		Pedido pedido = pedidoRepository.findById(dto.pedido().getIdPedido()).orElse(null);
		Produto produto = produtoRepository.findById(dto.produto().getIdProduto()).orElse(null);
		if(pedido == null || produto == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Pedido ou Produto não encontrado no banco de dados. ");
		}
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setQuantidade(dto.quantidade());
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		
		itemPedidoRepository.save(itemPedido);System.out.println("\nSalvo");
		return ResponseEntity.ok(itemPedido);
	}
	
	@PutMapping("/{id}")
	@Operation(
			summary = "Atualiza um item pedido existente", 
	        description = "Substitui os dados de um item pedido cadastrado pelo novo objeto enviado no corpo da requisição."
	)
	public ItemPedido atualizar(@PathVariable Integer id, @RequestBody ItemPedido pedidoAtualizado) {
		ItemPedido existe = itemPedidoRepository.findById(id).orElse(null);
		
		if(existe != null) {
			existe.setPedido(pedidoAtualizado.getPedido());
			existe.setProduto(pedidoAtualizado.getProduto());
			existe.setQuantidade(pedidoAtualizado.getQuantidade());
			
			return itemPedidoRepository.save(existe);
		}
		
		return null;
	}
	
	@DeleteMapping ("/{id}")
	@Operation(
			summary = "Remove um item pedido do banco", 
	        description = "Deleta permanentemente o item pedido do banco de dados com base no ID fornecido."
	)
	public String apagar(@PathVariable Integer id) {
		itemPedidoRepository.deleteById(id);
		
		return "Cadastro apagado!";
	}
}
