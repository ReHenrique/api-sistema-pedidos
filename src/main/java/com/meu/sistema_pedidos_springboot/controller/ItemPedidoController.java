package com.meu.sistema_pedidos_springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meu.sistema_pedidos_springboot.model.ItemPedido;
import com.meu.sistema_pedidos_springboot.model.Pedido;
import com.meu.sistema_pedidos_springboot.model.Produto;
import com.meu.sistema_pedidos_springboot.repository.ItemPedidoRepository;
import com.meu.sistema_pedidos_springboot.repository.PedidoRepository;
import com.meu.sistema_pedidos_springboot.repository.ProdutoRepository;

import dto.ItemPedidoRequestDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/item_pedidos")
public class ItemPedidoController {

	@Autowired 
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired 
	private PedidoRepository pedidoRepository; 
	@Autowired 
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<ItemPedido> buscarTodos(){
		return itemPedidoRepository.findAll();
	}
	@GetMapping("/{id}")
	public ItemPedido buscarPorId(@PathVariable Integer id) {
		return itemPedidoRepository.findById(id).orElse(null);
	}
	@PostMapping
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
	public String apagar(@PathVariable Integer id) {
		itemPedidoRepository.deleteById(id);
		
		return "Cadastro apagado!";
	}
}
