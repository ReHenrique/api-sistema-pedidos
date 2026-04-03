package dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

public record PessoaRequestDTO(
		@NotBlank(message = "Campo nome obrigatório!") String nome,
		@Past (message = "Nasceu no futuro é??") LocalDate dataNascimento
		) {

}
