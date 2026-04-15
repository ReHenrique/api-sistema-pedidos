# 📦 Sistema de Gerenciamento de Pedidos - API

Este projeto é uma API RESTful desenvolvida para gerenciar o fluxo de pedidos de uma loja, permitindo o controle de produtos, clientes e itens de pedido. Foi construído com foco em arquitetura limpa, boas práticas de desenvolvimento Back-end, integração com APIs externas e persistência de dados relacional.

## 🚀 Tecnologias Utilizadas
- **Java 21**: Utilização de recursos modernos da linguagem.
- **Spring Boot 3**: Framework principal para agilidade na configuração e desenvolvimento.
- **Spring Data JPA / Hibernate**: Abstração da camada de dados e mapeamento objeto-relacional.
- **MariaDB / MySQL**: Banco de dados relacional para persistência segura das informações.
- **SpringDoc OpenAPI (Swagger)**: Para documentação interativa e testes de endpoints.

## 🧠 O que eu aprendi e apliquei (Destaques Técnicos):
- **Arquitetura DTO com Records**: Implementação de `records` do Java 16+ para transferência de dados, garantindo imutabilidade e impedindo que as entidades do banco sejam expostas diretamente ao Front-end.
- **Integração com ViaCEP (RestClient)**: O sistema consome a API pública do governo de forma assíncrona para buscar e preencher automaticamente o endereço completo a partir do CEP.
- **Validações de Segurança Rigorosas**: Uso do *Jakarta Validation* (`@NotBlank`, `@NotNull`) para blindar a entrada de dados.
- **Validação Nativa de CPF**: Utilização da anotação `@CPF` do *Hibernate Validator* para garantir a integridade matemática dos documentos antes de chegarem ao banco.
- **Tratamento Global de Erros**: Implementação do padrão `@RestControllerAdvice` para interceptar falhas de validação e devolver mensagens JSON amigáveis e padronizadas.
- **Relacionamentos Complexos**: Mapeamento avançado de tabelas com chaves estrangeiras (`@ManyToOne`, `@OneToMany` e `@Embedded`) para ligar Pedidos, Itens, Produtos e Endereços.
- **Regras de Negócio**: Lógica de verificação manual no Controller para garantir consistência (ex: um pedido só é salvo se o produto e o cliente existirem).

## 📈 Evolução da Arquitetura

Este projeto está em constante evolução para refletir as melhores práticas do mercado:
> **Testes e Documentação:** Durante as fases iniciais, os testes e o mapeamento das rotas foram conduzidos via **Insomnia**. Atualmente, o projeto evoluiu para utilizar o **Swagger**, fornecendo uma documentação viva, interativa e sincronizada automaticamente com a base de código.

## ⚙️ Como rodar o projeto localmente:
1. Certifique-se de ter o **Java 21** e o **Maven** instalados na sua máquina.
2. Tenha um banco de dados **MariaDB** ou **MySQL** rodando (porta padrão 3306).
3. Clone este repositório:
   `git clone https://github.com/seu-usuario/sistema-pedidos-springboot.git`
4. Ajuste as credenciais do banco de dados (usuário e senha) no arquivo `src/main/resources/application.properties`.
5. Execute o projeto através da sua IDE (Eclipse/IntelliJ) ou via terminal com o comando `./mvnw spring-boot:run`.
6. Acesse a documentação interativa das rotas (Swagger) no seu navegador:
   `http://localhost:8080/swagger-ui/index.html`

---
*Desenvolvido por Renan Justino*