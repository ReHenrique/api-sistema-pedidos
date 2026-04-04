#  Sistema de Gerenciamento de Pedidos - API

Este projeto é uma API REST desenvolvida para gerenciar o fluxo de pedidos de uma loja, permitindo o controle de produtos, clientes e itens de pedido. Foi construído com foco em boas práticas de desenvolvimento Back-end e persistência de dados relacional.

##  Tecnologias Utilizadas
- **Java 21**: Utilização de recursos modernos como `record` para DTOs.
- **Spring Boot 3**: Framework principal para agilidade no desenvolvimento.
- **Spring Data JPA**: Para abstração da camada de dados e mapeamento objeto-relacional.
- **MariaDB / MySQL**: Banco de dados relacional para persistência.
- **Bean Validation (Jakarta Validation)**: Para blindagem da entrada de dados.

##  O que eu aprendi e apliquei:
- **Arquitetura DTO**: Implementação de `records` para transferência de dados, garantindo que as entidades do banco de dados não sejam expostas diretamente.
- **Validações de Segurança**: Uso de anotações como `@NotBlank`, `@NotNull` e `@Min` para garantir a integridade dos dados antes de chegarem ao banco.
- **Relacionamentos Complexos**: Mapeamento de tabelas com chaves estrangeiras (`@ManyToOne` e `@OneToMany`) para ligar Pedidos, Itens e Produtos.
- **Tratamento de Regras de Negócio**: Lógica de verificação manual no Controller para garantir que um pedido só seja salvo se o produto e o cliente existirem no banco.

##  Como rodar o projeto localmente:
1. Certifique-se de ter o **Java 21** e o **Maven** instalados.
2. Tenha um banco de dados **MariaDB** ou **MySQL** rodando (porta padrão 3306).
3. Clone este repositório.
4. Ajuste as credenciais do banco no arquivo `src/main/resources/application.properties`.
5. Execute o projeto através do Eclipse ou via terminal com `./mvnw spring-boot:run`.
6. Importe o arquivo `rotas-insomnia.json` (incluído na pasta raiz) no seu Insomnia para testar as rotas.

