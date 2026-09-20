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

## Testes

- **JUnit 5**
- **Mockito**
- **MockMcv**

## Funcionalidades

### Backend
- [x] Cadastrar filmes
- [x] Listar filmes cadastrados
- [x] Buscar filme por ID
- [x] Atualizar filmes
- [x] Excluir filmes
- [x] Teste automatizados no Controller
- [x] Persistência utilizando PostgreSQL
- [x] Enum para gêneros dos filmes
- [x] Tratamento global de exceções
- [x] Exceção personalizada para filme não encontrado
- [x] Testes automatizados do Service

### Frontend

- [x] Cadastro de filmes
- [x] Listagem dos filmes
- [x] Edição de filmes
- [x] Exclusão de filmes
- [x] Confirmação antes da exclusão
- [x] Validação dos dados do formulário
- [x] Mensagens de sucesso e erro
- [x] Modo de edição e cancelamento
- [x] Consumo da API utilizando fetch()
- [x] Event Delegation para os botões da tabela
- [x] Atualização da lista após as operações

## Arquitetura


    Frontend
    HTML + CSS + JavaScript
        ↓
        ↓ HTTP / JSON
    Controller
        ↓
    Service
        ↓ 
    Repository
        ↓
    PostgreSQL

### Controller
- **Responsável por receber as requisições HTTP e encaminhá-las para o Service**

### Service

- **Responsável pelas regras e operações relacionadas aos filmes**

### Repository

- **Responsável pela comunicação com o banco de dados através do Spring Data JPA**

### Frontend

- **Responsável pela interface com o usuário e pelo consumo da API REST**

## Fluxo da Aplicação

Um exemplo do fluxo de cadastro de um filme:

    Usuário
       │
       ▼
    Formulário HTML
       │
       ▼
    JavaScript
       │
       │ POST /filmes
       ▼
    FilmeController
       │
       ▼
    FilmeService
       │
       ▼
    FilmeRepository
       │
       ▼
    PostgreSQL

Após a operação, o frontend atualiza a lista de filmes através de uma nova requisição à API

## Banco de dados

O projeto utiliza PostgreSQL para armazenamento dos filmes.

A entidade principal é Filme possui os seguintes atributos:

    Campo	            Tipo	        Descrição
    
    id                  Long            Identificador único
    titulo	            String          Título do filme
    genero	            Enum            Gênero do filme
    anoLancamento	    Integer         Ano de lançamento
    nota	            Integer         Nota atribuída ao filme
    assistido           Boolean         Indica se o filme já foi assistido

- **O campo genero utiliza um enum Java e é persistido como String no banco de dados**
- **O Hibernate está configurado para atualizar automaticamente a estrutura do banco durante o desenvolvimento**

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

## Tratamento Global de Exceções

A aplicação possui tratamento global de exceções utilizando @RestControllerAdvice.

Foi criada uma exceção personalizada:

    FilmeNaoEncontradoException

Quando um filme não é encontrado, o Service lança essa exceção.

O tratamento global captura a exceção e retorna uma resposta HTTP adequada, evitando a necessidade de repetir o mesmo tratamento em cada Controller.

### Fluxo:

    Service
       │
       │ Filme não encontrado
       ▼
    FilmeNaoEncontradoException
       │
       ▼
    @RestControllerAdvice
       │
       ▼
    HTTP 404

## Frontend

Frontend

O frontend foi desenvolvido utilizando HTML, Bootstrap e JavaScript.

A comunicação com o backend é realizada através da função:

    fetch()

Os dados são enviados e recebidos utilizando JSON.

O JavaScript também é responsável por:

- **obter os dados do formulário;**
- **validar os dados;**
- **enviar requisições para a API;**
- **atualizar a tabela;**
- **preencher o formulário durante a edição;**
- **cancelar uma edição;**
- **solicitar confirmação antes da exclusão;**
- **exibir mensagens de sucesso ou erro.**


### Validação
O frontend possui validações antes de enviar os dados para a API.

Título

    O título não pode estar vazio.

Ano de lançamento

    O ano deve estar entre: 1888 e o ano atual.

- **O ano de 1888 foi utilizado como referência histórica para o primeiro filme registrado**

O ano atual é obtido automaticamente através do JavaScript:

    new Date().getFullYear()

Assim, não é necessário alterar manualmente o valor a cada ano.

Nota

    A nota deve estar entre: 1 e 5

Quando uma validação falha, o envio para a API é interrompido e uma mensagem é apresentada ao usuário.

## Event Delegation

Event Delegation

Os botões de Editar e Excluir da tabela utilizam Event Delegation.

Em vez de adicionar um evento click individual para cada botão, o evento é registrado no elemento pai:

    tabela.addEventListener("click", function(event) {

O JavaScript verifica qual botão foi clicado através de:

    event.target

Essa abordagem permite trabalhar com os botões mesmo quando a tabela é atualizada dinamicamente.

## Testes automatizados

O projeto possui testes automatizados utilizando:

- **JUnit 5**
- **Mockito**
- **MockMvc**

Os testes foram separados de acordo com as camadas da aplicação.

### Testes do Service

O FilmeService é testado utilizando mocks para simular o comportamento do FilmeRepository.

São testados cenários como:

- **salvar filme;**
- **buscar filme;**
- **filme não encontrado;**
- **atualizar filme;**
- **tentar atualizar filme inexistente;**
- **excluir filme;**
- **tentar excluir filme inexistente.**

O Mockito é utilizado para simular comportamentos com recursos como:

    when()
    thenReturn()
    verify()

Também são utilizados recursos como:

    Optional.of()
    Optional.empty()
    assertTrue()
    assertFalse()
    assertEquals()
    assertThrows()

### Testes do Controller

O FilmeController é testado utilizando MockMvc.

O FilmeService é simulado para que o Controller possa ser testado isoladamente.

São testados os principais endpoints:

- **GET /filmes**
- **GET /filmes/{id}**
- **POST /filmes**
- **PUT /filmes/{id}**
- **DELETE /filmes/{id}**

Também são verificados diferentes códigos HTTP, como:

    200 OK
    204 No Content
    404 Not Found

Exemplo:

    mockMvc.perform(get("/filmes/1"))
    .andExpect(status().isOk());

O MockMvc permite simular requisições HTTP sem precisar utilizar o Postman.

## Estrutura do projeto

    src/ 
    ├── main/
    │ ├── java/ 
    │ │ └── br/com/marcos/catalogo_filmes/
    │ │                   ├── controller/
    │ │                   │   └── FilmeController.java
    │ │                   │   
    │ │                   ├── enums/ 
    │ │                   │   └── Genero.java 
    │ │                   │  
    │ │                   ├── exception/
    │ │                   │   ├── FilmeNaoEncontradoException.java 
    │ │                   │   └── GlobalExceptionHandler.java
    │ │                   │   
    │ │                   ├── model/ 
    │ │                   │   └── Filme.java
    │ │                   │   
    │ │                   ├── repository/ 
    │ │                   │   └── FilmeRepository.java 
    │ │                   │
    │ │                   ├── service/ 
    │ │                   │   └── FilmeService.java 
    │ │                   │
    │ │                   └── CatalogoFilmesApplication.java
    │ │  
    │ └── resources/ 
    │     ├── static/ 
    │     │   ├── bootstrap/
    │     │   ├── js/
    │     │   │ └── app.js 
    │     │   └── index.html 
    │     │ 
    │     └── application.properties/
    │     
    └── test/ 
        └── java/
            └── br/com/marcos/catalogo_filmes/
                              ├── controller/
                              │   └── FilmeControllerTest.java
                              │
                              ├── service/ 
                              │   └── FilmeServiceTest.java
                              │
                              └── CatalogoFilmesApplicationTests.java

## Como executar o projeto

### Pré-requisitos

Antes de executar a aplicação, é necessário ter instalado:

- **Java 21 ou superior**
- **PostgreSQL**
- **Maven ou utilizar o Maven Wrapper disponibilizado no projeto**
- **IntelliJ IDEA ou outra IDE de preferência**

. Clone o repositório

    git clone <URL_DO_REPOSITORIO>
. Acesse a pasta do projeto
   
    cd catalogo-de-filmes.java

### 1. Configurar banco de dados PostgreSQL

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

O Spring Boot iniciará a aplicação na porta:

    8080

### 3. Acesse a aplicação

Com o servidor iniciado, acesse:

    http://localhost:8080

## Melhorias futuras

Algumas melhorias planejadas para futuras versões do projeto:

- [ ] Implementar DTOs;

- [ ] Adicionar validações no backend;

- [ ] Padronizar respostas de erro da API;

- [ ] Implementar filtros por título, gênero, nota e status;

- [ ] Implementar paginação e ordenação;

- [ ] Criar testes de integração;

- [ ] Melhorar a experiência da interface;

- [ ] Adicionar documentação da API com Swagger/OpenAPI.

Objetivo do projeto

Este projeto foi desenvolvido principalmente como forma de estudo e prática de conceitos relacionados ao desenvolvimento de aplicações web.

Durante o desenvolvimento foram praticados conceitos como:

- **Java;**
- **Spring Boot;**
- **APIs REST;**
- **arquitetura em camadas;**
- **Spring Data JPA;**
- **Hibernate;**
- **PostgreSQL;**
- **HTML;**
- **CSS;**
- **Bootstrap;**
- **JavaScript;**
- **Consumo de APIs;**
- **Validações;**
- **Tratamento global de exceções;**
- **JUnit;**
- **Mockito;**
- **MockMvc;**
- **Clean Code;**
- **Event Delegation.**

O projeto também serve como material de estudo para preparação para oportunidades de desenvolvimento Java Júnior.

## Autor

### Marcos Willian Manoel da Silveira

Projeto desenvolvido para fins de estudo, prática e construção de portfólio.