package dto;

import java.time.LocalDate;

import com.meu.sistema_pedidos_springboot.model.Pessoa;

import jakarta.validation.constraints.NotNull;

public record PedidoRequestDTO(
		@NotNull (message = "Data do pedido obrigatorio!") LocalDate data,
		@NotNull (message ="ID da pessoa obrigatorio!") Pessoa pessoa
		
		) {

}
