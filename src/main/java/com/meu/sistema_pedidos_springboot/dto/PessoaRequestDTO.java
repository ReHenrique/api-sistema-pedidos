package com.meu.sistema_pedidos_springboot.dto;
import java.time.LocalDate;
import org.hibernate.validator.constraints.br.CPF;
import jakarta.validation.constraints.*;

public record PessoaRequestDTO(
		@NotBlank(message = "O nome é obrigatório")
	    String nome,
	    @NotBlank(message = "O CPF é obrigatório")
	    @CPF(message = "O CPF informado é inválido") 
	    String cpf,

	    @NotNull(message = "A data de nascimento é obrigatória")
	    LocalDate dataNascimento,

	    @NotBlank(message = "O CEP é obrigatório")
	    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter 8 números sem traços")
	    String cep,

	    @NotBlank(message = "O número da casa é obrigatório")
	    String numero,

	    String complemento
		) {

}
