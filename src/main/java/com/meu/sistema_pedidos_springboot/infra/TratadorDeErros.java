package com.meu.sistema_pedidos_springboot.infra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> tratarErroDeValidacao(MethodArgumentNotValidException ex){
		
		Map<String, String> erros = new HashMap<>();
		for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            erros.put(erro.getField(), erro.getDefaultMessage());
        }
		return ResponseEntity.badRequest().body(erros);
	}
}
