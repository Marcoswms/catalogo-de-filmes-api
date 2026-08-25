<div align="center">

# Catálogo de Filmes
###

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18.4-336791?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-4.0+-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

</div>

---

## Sobre o projeto:

O Catálogo de Filmes é uma aplicação web desenvolvida para o gerenciamento de filmes, permitindo cadastrar, consultar, atualizar e remover registros.

O projeto foi desenvolvido com Java e Spring Boot, utilizando uma API REST para comunicação entre o backend e o frontend. Os dados são armazenados em um banco de dados PostgreSQL e o frontend utiliza HTML, CSS, Bootstrap e JavaScript.

O projeto tem como objetivo colocar em prática conceitos de desenvolvimento de aplicações web, criação de APIs REST, persistência de dados, integração entre frontend e backend e operações CRUD.

## Stack

### Backend

- **Java 21** + **Spring Boot 4.1.0**
- **Spring Data JPA**
- **Spring Web MVC**
- **Hibernate**
- **Maven** — gerenciador de dependências

### Banco de dados

- **PostgreSql 18.4** — banco de dados

### Frontend

- **Html5**
- **CSS3**
- **Bootstrap**
- **JavaScript**

## Funcionalidades

- **Cadastrar filmes**
- **Listar filmes cadastrados**
- **Buscar filme por ID**
- **Atualizar filmes**
- **Excluir filmes**
- **Informar gênero do filme**
- **Informar ano de lançamento**
- **Atribuir nota de 1 a 5**
- **informar se o filme já foi assistido**
- **Validar dados do formulário**
- **Editar filmes diretamente pela interface**
- **Excluir filmes diretamente pela interface**
- **Exibir mensagem de sucesso e erro ao usuário**

## Arquitetura

    Controller
        ↓
    Service
        ↓ 
    Repository
        ↓
    PostgreSQL

### Controller
- **Responsável por receber as requisições HTTP e disponibilizar os endpoints da API**

### Service

- **Responsável pelas regras e operações relacionadas aos filmes**

### Repository

- **Responsável pela comunicação com o banco de dados através do Spring Data JPA**

### Model

- **Representa a entidade Filme, que é persistida na tabela filmes**

### Enum

- **A aplicação utiliza o enum Genero para definir os gêneros disponíveis para os filmes**

## API REST

A API utiliza o prefixo:

    /filmes
Listar todos os filmes

    GET /filmes
Buscar filme por ID

    GET /filmes/{id}
Cadastrar filme
    
    POST /filmes

Exemplo de corpo da requisição:

    {
    "titulo": "Interestelar",
    "genero": "FICCAO_CIENTIFICA",
    "anoLancamento": 2014,
    "nota": 5,
    "assistido": true
    }
Atualizar filme

    PUT /filmes/{id}

Exemplo:

    {
    "titulo": "Interestelar",
    "genero": "FICCAO_CIENTIFICA",
    "anoLancamento": 2014,
    "nota": 5,
    "assistido": true
    }
Excluir filme

    DELETE /filmes/{id}

Em caso de sucesso, a API retorna:

    204 No Content

Caso o filme não seja encontrado, a API retorna:

    404 Not Found

## Gêneros disponíveis

Os gêneros são representados pelo enum Genero:

    ACAO
    AVENTURA
    COMEDIA
    DRAMA
    FICCAO_CIENTIFICA
    FANTASIA
    ROMANCE
    SUSPENSE
    TERROR
    DOCUMENTARIO
    ANIMACAO

## Banco de dados

O projeto utiliza PostgreSQL para armazenamento dos filmes.

A entidade Filme possui os seguintes atributos:

    Campo	            Tipo	        Descrição
    
    id                  Long            Identificador único
    titulo	            String          Título do filme
    genero	            Enum            Gênero do filme
    anoLancamento	    Integer         Ano de lançamento
    nota	            Integer         Nota atribuída ao filme
    assistido           Boolean         Indica se o filme já foi assistido

O Hibernate está configurado para atualizar automaticamente a estrutura do banco durante o desenvolvimento

## Como executar o projeto

### Pré-requisitos

Antes de executar a aplicação, é necessário ter instalado:

- **Java 21 ou superior**
- **PostgreSQL**
- **Maven ou utilizar o Maven Wrapper disponibilizado no projeto**

. Clone o repositório

    git clone <URL_DO_REPOSITORIO>
. Acesse a pasta do projeto
   
    cd catalogo-de-filmes.java

### 1. Configure o PostgreSQL

Crie um banco de dados chamado:

    catalogofilmes

As configurações atuais da aplicação estão definidas no arquivo:

    src/main/resources/application.properties

Por padrão, o projeto está configurado para:

    spring.datasource.url=jdbc:postgresql://localhost:5433/catalogofilmes
    spring.datasource.username=postgres
    spring.datasource.password=postgres

Importante: altere usuário, senha e porta de acordo com a configuração do seu ambiente.

### 2. Execute a aplicação

Utilizando o Maven Wrapper:

. Windows:

    mvnw.cmd spring-boot:run

. Linux/macOS:

    ./mvnw spring-boot:run

Ou execute a classe:

    CatalogoFilmesApplication

### 3. Acesse a aplicação

Com o servidor iniciado, acesse:

    http://localhost:8080

## Estrutura do projeto

    src/ 
    ├── main/
    │ ├── java/ 
    │ │ └── br/com/marcos/catalogo_filmes/ 
    │ │ ├── controller/
    │ │ │ └── FilmeController.java 
    │ │ ├── enums/ 
    │ │ │ └── Genero.java 
    │ │ ├── model/ 
    │ │ │ └── Filme.java 
    │ │ ├── repository/ 
    │ │ │ └── FilmeRepository.java 
    │ │ ├── service/ 
    │ │ │ └── FilmeService.java 
    │ │ └── CatalogoFilmesApplication.java
    │ │  
    │ └── resources/ 
    │     ├── static/ 
    │     │ ├── bootstrap/
    │     │ ├── js/
    │     │ │ └── app.js 
    │     │ └── index.html 
    │     │ 
    │     └── application.properties/
    │     
    └── test/ 
        └── java/
            └── br/com/marcos/catalogo_filmes/
                └── CatalogoFilmesApplicationTests.java

## Melhorias futuras

Algumas melhorias planejadas para futuras versões do projeto:

- [ ] Implementar DTOs;

- [ ] Melhorar o tratamento global de exceções;

- [ ] Padronizar respostas de erro da API;

- [ ] Adicionar validações no backend;

- [ ] Implementar filtros por título, gênero, nota e status;

- [ ] Implementar paginação e ordenação;

- [ ] Criar testes unitários e de integração;

- [ ] Melhorar a experiência da interface;

- [ ] Adicionar documentação da API com Swagger/OpenAPI.

## Autor

### Marcos Willian Manoel da Silveira

Projeto desenvolvido para estudos e prática de desenvolvimento de aplicações web utilizando Java, Spring Boot e PostgreSQL