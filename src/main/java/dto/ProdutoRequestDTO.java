package dto;

import jakarta.validation.constraints.NotBlank;

public record ProdutoRequestDTO(@NotBlank(message = "O nome do produto é obrigatório! Não pode ficar em branco.")
	String nome) {

}
