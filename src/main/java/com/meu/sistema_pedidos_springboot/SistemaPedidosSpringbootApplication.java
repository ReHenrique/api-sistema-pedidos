package com.meu.sistema_pedidos_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "API de Sistema de Pedidos", version = "", description = "Desenvolvido por Renan Justino"))
@SpringBootApplication
public class SistemaPedidosSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaPedidosSpringbootApplication.class, args);
	}

}
