# 📦 Sistema de Gerenciamento de Pedidos - API

Este projeto é uma API RESTful desenvolvida para gerenciar o fluxo de pedidos de uma loja, permitindo o controle de produtos, clientes e itens de pedido. Foi construído com foco em arquitetura limpa, boas práticas de desenvolvimento Back-end, integração com APIs externas e persistência de dados relacional.

## 🚀 Tecnologias Utilizadas
- **Java 21**: Utilização de recursos modernos da linguagem.
- **Spring Boot 3**: Framework principal para agilidade na configuração e desenvolvimento.
- **Spring Data JPA / Hibernate**: Abstração da camada de dados e mapeamento objeto-relacional.
- **MariaDB / MySQL**: Banco de dados relacional para persistência segura das informações.
- **SpringDoc OpenAPI (Swagger)**: Para documentação interativa e testes de endpoints.
- **Docker**: Containerização da aplicação para garantir portabilidade e consistência entre ambientes.

## 🧠 O que eu aprendi e apliquei (Destaques Técnicos):
- **Arquitetura DTO com Records**: Implementação de `records` do Java 16+ para transferência de dados, garantindo imutabilidade e impedindo que as entidades do banco sejam expostas diretamente ao Front-end.
- **Integração com ViaCEP (RestClient)**: O sistema consome a API pública do governo de forma assíncrona para buscar e preencher automaticamente o endereço completo a partir do CEP.
- **Validações de Segurança Rigorosas**: Uso do *Jakarta Validation* (`@NotBlank`, `@NotNull`) para blindar a entrada de dados.
- **Validação Nativa de CPF**: Utilização da anotação `@CPF` do *Hibernate Validator* para garantir a integridade matemática dos documentos antes de chegarem ao banco.
- **Tratamento Global de Erros**: Implementação do padrão `@RestControllerAdvice` para interceptar falhas de validação e devolver mensagens JSON amigáveis e padronizadas.
- **Infraestrutura com Docker**: Empacotamento da aplicação em um container otimizado (Alpine Linux), utilizando variáveis de ambiente e mapeamento de rede (`host.docker.internal`) para comunicação isolada com o banco de dados.
- **Relacionamentos Complexos**: Mapeamento avançado de tabelas com chaves estrangeiras (`@ManyToOne`, `@OneToMany` e `@Embedded`) para ligar Pedidos, Itens, Produtos e Endereços.
- **Regras de Negócio**: Lógica de verificação manual no Controller para garantir consistência (ex: um pedido só é salvo se o produto e o cliente existirem).

## 📈 Evolução da Arquitetura

Este projeto está em constante evolução para refletir as melhores práticas do mercado:
> **Testes e Documentação:** Durante as fases iniciais, os testes e o mapeamento das rotas foram conduzidos via **Insomnia**. Atualmente, o projeto evoluiu para utilizar o **Swagger**, fornecendo uma documentação viva, interativa e sincronizada automaticamente com a base de código.

> **Infraestrutura e Deploy:** A API evoluiu de uma execução puramente local para um ambiente isolado e portátil via **Docker**, demonstrando capacidade de gerenciar o ciclo de vida da aplicação independente do sistema operacional hospedeiro.

## ⚙️ Como rodar o projeto

### Opção 1: Execução via Docker (Recomendado)
Esta é a maneira mais rápida de testar a aplicação em qualquer máquina, sem precisar instalar o Java.

1. Certifique-se de ter o **Docker Desktop** rodando na sua máquina.
2. Tenha um banco de dados **MariaDB** rodando na porta `3306` (com um banco criado, ex: `loja_web`).
3. Clone este repositório:
   `git clone https://github.com/seu-usuario/sistema-pedidos-springboot.git`
4. Na pasta raiz do projeto, gere o pacote da aplicação:
   `.\mvnw clean package -DskipTests`
5. Construa a imagem Docker:
   `docker build -t sistema-pedidos .`
6. Inicie o container injetando a configuração do seu banco de dados (ajuste a URL, usuário e senha se necessário):
   `docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mariadb://host.docker.internal:3306/loja_web sistema-pedidos`

### Opção 2: Execução Local (Maven / IDE)
1. Certifique-se de ter o **Java 21** e o **Maven** instalados.
2. Ajuste as credenciais do banco de dados no arquivo `src/main/resources/application.properties`.
3. Execute o projeto através da sua IDE (Eclipse/IntelliJ) ou via terminal com o comando `./mvnw spring-boot:run`.

## 📖 Acessando a Documentação
Com o projeto rodando (seja via Docker ou Localmente), acesse a documentação interativa (Swagger) no seu navegador:
`http://localhost:8080/swagger-ui/index.html`

---
*Dev Renan Justino*