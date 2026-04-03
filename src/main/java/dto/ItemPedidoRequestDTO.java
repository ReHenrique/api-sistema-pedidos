package dto;

import com.meu.sistema_pedidos_springboot.model.Pedido;
import com.meu.sistema_pedidos_springboot.model.Produto;

import jakarta.validation.constraints.*;

public record ItemPedidoRequestDTO(
		
				@NotNull(message = "A quantidade é obrigatória") 
				@Min(value = 1, message = "A quantidade deve ser de pelo menos 1 item") 
				Integer quantidade,
				
				@NotNull(message = "O pedido é obrigatório") 
				Pedido pedido,
				
				@NotNull(message = "O produto é obrigatório") 
				Produto produto
		) {

}
