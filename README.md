# Projeto API Rest com Java Spring Boot

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)![SpingBoot](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

Está aplicação é uma API Rest com Java e Spring Boot. Está sendo desenvolvida com o objetivos de estudos para aplicações back-end.

---


## Sumário

## Sumário

- [Descrição](#descrição)
- [Tecnologias](#tecnologias)
- [Dependências](#dependências)
- [Instruções](#instruções)
    - [Banco PostgreSQL com Docker](#banco-postgres-com-docker)
    - [Controle de versão com Flyway](#controle-de-versão-com-flyway)
- [Documentação](#documentação)
    - [Endpoints](#endpoints)
    - [Parâmetros de Requisições](#parâmetros-de-requisição)
    - [Validações](#validações)
- [Descrição da API](#descrição-da-api)
    - [Entidades](#entidades)
    - [Controladores](#controladores)
    - [Repositórios](#repositórios)
    - [Objetos de Transferência de Dados (DTOs)](#objetos-de-transferência-de-dados-dtos)
- [Resumo](#resumo)

---

## Descrição
Este projeto é uma API REST desenvolvida com Java e Spring Boot.
Ele foi criado com o objetivo de estudar e aprimorar as habilidades de
desenvolvimento back-end com Java.
O contexto da proposta é um uma API de um sistema de gerenciamento de clinica médica.
O objetivo é desenvolver um MVP de uma API que possa responder as requisições minímas necessárias para a aplicação.

---

## Tecnologias
- Java v23
- Spring 
- Spring Boot v3.3.3
- Postgres lts
- Docker


---

## Dependências

- org.springframework.boot:spring-boot-starter-web
- org.springframework.boot:spring-boot-devtools
- org.projectlombok:lombok:1.18.34
- org.springframework.boot:spring-boot-starter-test
- org.springframework.boot:spring-boot-starter-data-jpa
- org.springframework.boot:spring-boot-starter-validation
- org.flywaydb:flyway-core
- org.flywaydb:flyway-database-postgresql
- org.postgresql:postgresql
- org.springframework.boot:spring-boot-starter-security
- org.springframework.security:spring-security-test
- com.auth0:java-jwt:4.4.0

---

## Instruções

Para iniciar a aplicação da sua API de teste, siga os passos abaixo:

### 1. Clone o repositório
Primeiro, clone o repositório da aplicação para o seu ambiente local.

```bash
git clone https://github.com/mauroyaga/java-spring-boot-api-rest-gerenciamento-clinica.git
```

Para testar esta a bordagem do banco de dados, você pode usar o Docker para criar um 
container com uma instância do PostgreSQL que sera inicializada com as configurações
definidas no arquivo ***docker-compose.yml***. automaticamente.

---

## Banco Postgres com Docker

Com o docker devidamente instalado em seu ambiente, no terminal na raiz do projeto execute o seguinte comando:

```bash
  docker-compose up -d
```
Através do arquivo ***docker-compose.yml***, o docker irá criar um container com uma instância do PostgreSQL.

```bash
  docker ps
```
Você deverá ver uma saída semelhante a esta:
```bash
  CONTAINER ID   IMAGE     COMMAND        CREATED         STATUS         PORTS           NAMES
  b3b3b3b3b3b3   postgres  "docker-entryp…"  5 seconds ago   Up 4 seconds
```
Agora que o container do PostgreSQL está rodando, você pode acessar o banco de dados
usando um cliente SQL como o DBeaver ou o pgAdmin.
Para isso, você precisara das credenciais que estão no arquivo ***application.properties*** do projeto.

***Credênciais***
- Host: localhost
- Porta: 5432
- Database: gerenciamento_clinica_db
- Username: postgres
- Password: root

Com a imagem do Docker do PostgreSQL rodando, você pode iniciar a aplicação Spring Boot.
Com a aplicação rodando, o Flyway irá criar as tabelas no banco de dados automaticamente.
Agora você pode testar a API com o Postman ou o Insomnia, para consumir os endpoints.

---

## Controle de versão com Flyway

Flyway é uma ferramenta de migração de banco de dados que ajuda a gerenciar e aplicar alterações
no esquema do banco de dados. Ele rastreia o histórico de alterações do esquema do banco de dados
e aplica as migrações quando necessário. 

As migrações são scripts SQL que estão localizados no
diretório ***src/main/resources/db/migration*** do projeto. Cada arquivo de migração tem um nome que começa
com V, seguido por uma versão e uma descrição. Por exemplo, V1__Create_medico_table.sql poderia ser o nome
de uma migração que cria a tabela medico.  

Quando você inicia sua aplicação, o Flyway verifica o estado atual
do seu banco de dados e compara com as migrações disponíveis em seu projeto. Se houver migrações que ainda não
foram aplicadas, o Flyway as executará em ordem para atualizar o banco de dados.
Caso faça alterações no banco de dados, crie uma nova migration para que o Flyway possa atualizar o banco de dados.

***Obs:***
È importante sempre parar o projeto ao criar os arquivos de migrations,
para evitar que o Flyway os execute antes da hora, com o código ainda
incompleto, causando com isso problemas.

Entretanto, eventualmente pode acontecer de esquecermos de parar o projeto
e algum erro acontecer ao tentar inicializar a aplicação.
 Nesse caso será exibido o seguinte erro ao tentar inicializar a aplicação:
 

```bash
Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'flywayInitializer' defined in class path resource [org/springframework/boot/autoconfigure/flyway/FlywayAutoConfiguration$FlywayConfiguration.class]: Validate failed: Migrations have failed validation
```

Perceba na mensagem de erro que é indicado que alguma migration falhou,
impedindo assim que o projeto seja inicializado corretamente.
Esse erro também pode acontecer se o código da migration estiver inválido,
contendo algum trecho de SQL digitado de maneira incorreta.

Para resolver esse problema será necessário acessar o banco de dados da aplicação
e executar o seguinte comando sql:


```bash
delete from flyway_schema_history where success = 0;
```

O comando anterior serve para apagar na tabela do Flyway todas as migrations
cuja execução falhou. Após isso, basta corrigir o código da migration e executar novamente o projeto.

Obs: Pode acontecer de alguma migration ter criado uma tabela e/ou colunas
e com isso o problema vai persistir, pois o flyway não vai apagar as tabelas/colunas
criadas em migrations que falharam. Nesse caso você pode apagar o banco de dados e criá-lo novamente:

```bash
drop database gerenciamento_clinica_db;
create gerenciamento_clinica_db;
```

---

## Documentação

### EndPoints
Todas as Rotas: 

- http://localhost:8080/medicos

- http://localhost:8080/medicos

- http://localhost:8080/pacientes

- http://localhost:8080/agendas


**Cadastro de Médico (POST)**

- rota: http://localhost:8080/medicos


```bash
{
    "nome": "Maria Oliveira",
    "email": "maria.oliveira@example.com",
    "telefone": "987654321",
    "especialidade": "CARDIOLOGIA",
    "crm": "67890",
    "endereco": {
        "logradouro": "Avenida Paulista",
        "bairro": "Bela Vista",
        "cep": "87654321",
        "cidade": "São Paulo",
        "uf": "SP",
        "complemento": "Sala 202",
        "numero": "456"
    }
}

```

**Listagem de Médicos (GET)**

Para listar todos os médicos, basta acessar a rota abaixo:

```bash
http://localhost:8080/medicos
```

**Atualização de Médico (PUT)**

Este é um exemplo de requisição para atualizar as informações de um médico.
do post anterior, caso o banco não esteja populado.

```bash
{
    "id": 1,
	"nome" : "Maria Oliveira Silva"
}
```
**Deleção de Médico (DELETE)**

- Forneça o id do médico que deseja deletar nofinal da rota.
```bash
http://localhost:8080/medicos/1
```

#### Parametros de requisição

**Paginação**

**page***: Este parâmetro define o número da página que você deseja recuperar.
A contagem de páginas começa em 0, então page=0 recuperará a primeira página,
page=1 recuperará a segunda página, e assim por diante.

***size***: Este parâmetro define o número de itens que você deseja por página.
Por exemplo, size=20 retornará 20 itens por página.

***Obs:*** O tamanho padrão da página é 20 itens.

***Ordenação***

***sort***: Este parâmetro define o campo pelo qual você deseja ordenar
os resultados e a direção da ordenação. Por exemplo, sort=nome,asc ordenará os resultados
pelo campo nome em ordem ascendente, e sort=nome,desc ordenará em ordem descendente.

---

 **Listagem Paginada de Médicos**
 
 Ao configurar a paginação, a API deve retornar uma lista de médicos com base
 nos parâmetros de paginação passados na requisição. Ou seja, a paginação é controlada
 por quem for consumir a api.
 A API deve retornar uma lista de médicos com base nos parâmetros de paginação passados na requisição.


 Exemplos de parametros de paginação:

 1) A requisição a baixo retorna 1 resultado por página, da segunda página.

```bash
localhost:8080/medicos?size=1&page=1
```

 2) A requisição a baixo retornar a lista de médicos,
 ordenados pelo campo nome em ordem ascendente por padrão.

 ```bash
 localhost:8080/medicos?sort=nome
```

3) A requisição retorna a lista de medicos com a ordenação decrescente pelo campo crm.


```bash
localhost:8080/medicos?sort=crm,desc
```
4) A requisição retorna a lista com crm decrescente com 2 elementos por página.

```bash
localhost:8080/medicos?sort=crm,desc&size=2
```

---

### Validações

A classe `SecurityConfiguration` é uma configuração de segurança para a aplicação Spring Boot. 
Aqui está uma explicação detalhada do que foi feito:

1. **Anotações**:
    - `@Configuration`: Indica que a classe é uma fonte de definições de beans para o contexto da aplicação.
    - `@EnableWebSecurity`: Habilita a segurança web no projeto Spring.
   

2. **Método `securityFilterChain`**:
    - Define um bean do tipo `SecurityFilterChain` que configura a segurança HTTP.
    - Desabilita a proteção CSRF (Cross-Site Request Forgery) usando `http.csrf(AbstractHttpConfigurer::disable)`.
    - Configura a política de criação de sessão como `STATELESS`, indicando que a 
    - aplicação não deve manter estado de sessão entre as requisições.
   

3. **Método `authenticationManager`**:
    - Define um bean do tipo `AuthenticationManager`.
    - Obtém o `AuthenticationManager` a partir da configuração de autenticação
    - fornecida pelo Spring (`AuthenticationConfiguration`).


4. **Método `passwordEncoder`**:
    - Define um bean do tipo `PasswordEncoder`.
    - Retorna uma instância de `BCryptPasswordEncoder`, que é usada para criptografar senhas.
   

Essas configurações são essenciais para definir como a segurança será gerenciada na 
aplicação, incluindo a autenticação e a codificação de senhas.

link para a documentação das validações, com o validation do Spring Boot

[https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#builtinconstraints](https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#builtinconstraints)

---

## Descrição da API

A API é desenvolvida usando Spring Boot e segue uma arquitetura modular para gerenciar diferentes entidades como `Medico`, `Paciente` e `Agenda`. Abaixo está uma descrição detalhada da estrutura e funcionalidade da API:

### Entidades

1. **Medico**:
    - Representa um médico no sistema.
    - Gerenciado pelo `MedicoController`.

2. **Paciente**:
    - Representa um paciente no sistema.
    - Gerenciado pelo `PacienteController`.

3. **Agenda**:
    - Representa informações de agendamento.
    - Gerenciado pelo `AgendaController`.

### Controladores

1. **MedicoController**:
    - **Endpoints**:
        - `POST /medicos`: Registra um novo médico.
        - `GET /medicos`: Lista todos os médicos ativos com paginação.
        - `PUT /medicos`: Atualiza informações do médico.
        - `DELETE /medicos/{id}`: Exclui um médico pelo ID.
        - `GET /medicos/{id}`: Recupera informações detalhadas de um médico pelo ID.

2. **PacienteController**:
    - **Endpoints**:
        - `POST /pacientes`: Registra um novo paciente.
        - `GET /pacientes`: Lista todos os pacientes ativos com paginação.
        - `PUT /pacientes`: Atualiza informações do paciente.
        - `DELETE /pacientes/{id}`: Exclui um paciente pelo ID.
        - `GET /pacientes/{id}`: Recupera informações detalhadas de um paciente pelo ID.

3. **AgendaController**:
    - **Endpoints**:
        - `GET /agenda`: Lista todas as informações de agendamento com paginação.
        - `PUT /agenda`: Atualiza informações de agendamento.
        - `DELETE /agenda/{id}`: Exclui informações de agendamento pelo ID.

4. **AutenticacaoController**:
    - **Endpoints**:
        - `POST /login`: Autentica um usuário e retorna um token JWT.

### Segurança

- **SecurityConfiguration**:
    - Configura as definições de segurança da API.
    - Desabilita a proteção CSRF.
    - Define a gestão de sessão como stateless.
    - Configura a codificação de senha usando `BCryptPasswordEncoder`.

### Repositórios

- **MedicoRepository**: Gerencia operações de banco de dados para entidades `Medico`.
- **PacienteRepository**: Gerencia operações de banco de dados para entidades `Paciente`.
- **AgendaRepository**: Gerencia operações de banco de dados para entidades `Agenda`.

### Objetos de Transferência de Dados (DTOs)

- **DadosCadastroMedico**: Dados para registrar um novo médico.
- **DadosListagemMedico**: Dados para listar médicos.
- **DadosDetalhamentoMedico**: Dados detalhados de um médico.
- **DadosAtualizacaoMedico**: Dados para atualizar um médico.
- **DadosCadastroPaciente**: Dados para registrar um novo paciente.
- **DadosListagemPaciente**: Dados para listar pacientes.
- **DadosDetalhamentoPaciente**: Dados detalhados de um paciente.
- **DadosAtualizacaoPaciente**: Dados para atualizar um paciente.
- **DadosAutenticacao**: Dados para autenticação de usuário.
- **Dadostoken**: Dados para o token JWT.

### Resumo
# Descrição da API

A API é desenvolvida usando **Spring Boot** e segue uma arquitetura modular para gerenciar diferentes entidades como **Medico**, **Paciente** e **Agenda**. Abaixo está uma descrição detalhada da estrutura e funcionalidade da API.

## Entidades

- **Medico**  
  Representa um médico no sistema.  
  Gerenciado pelo `MedicoController`.

- **Paciente**  
  Representa um paciente no sistema.  
  Gerenciado pelo `PacienteController`.

- **Agenda**  
  Representa informações de agendamento.  
  Gerenciado pelo `AgendaController`.

## Controladores

### MedicoController

Endpoints:
- `POST /medicos`: Registra um novo médico.
- `GET /medicos`: Lista todos os médicos ativos com paginação.
- `PUT /medicos`: Atualiza informações do médico.
- `DELETE /medicos/{id}`: Exclui um médico pelo ID.
- `GET /medicos/{id}`: Recupera informações detalhadas de um médico pelo ID.

### PacienteController

Endpoints:
- `POST /pacientes`: Registra um novo paciente.
- `GET /pacientes`: Lista todos os pacientes ativos com paginação.
- `PUT /pacientes`: Atualiza informações do paciente.
- `DELETE /pacientes/{id}`: Exclui um paciente pelo ID.
- `GET /pacientes/{id}`: Recupera informações detalhadas de um paciente pelo ID.

### AgendaController

Endpoints:
- `GET /agenda`: Lista todas as informações de agendamento com paginação.
- `PUT /agenda`: Atualiza informações de agendamento.
- `DELETE /agenda/{id}`: Exclui informações de agendamento pelo ID.

### AutenticacaoController

Endpoints:
- `POST /login`: Autentica um usuário e retorna um token JWT.

## Segurança

**SecurityConfiguration**:
- Configura as definições de segurança da API.
- Desabilita a proteção CSRF.
- Define a gestão de sessão como stateless.
- Configura a codificação de senha usando `BCryptPasswordEncoder`.

## Repositórios

- **MedicoRepository**: Gerencia operações de banco de dados para entidades `Medico`.
- **PacienteRepository**: Gerencia operações de banco de dados para entidades `Paciente`.
- **AgendaRepository**: Gerencia operações de banco de dados para entidades `Agenda`.

## Objetos de Transferência de Dados (DTOs)

- **DadosCadastroMedico**: Dados para registrar um novo médico.
- **DadosListagemMedico**: Dados para listar médicos.
- **DadosDetalhamentoMedico**: Dados detalhados de um médico.
- **DadosAtualizacaoMedico**: Dados para atualizar um médico.
- **DadosCadastroPaciente**: Dados para registrar um novo paciente.
- **DadosListagemPaciente**: Dados para listar pacientes.
- **DadosDetalhamentoPaciente**: Dados detalhados de um paciente.
- **DadosAtualizacaoPaciente**: Dados para atualizar um paciente.
- **DadosAutenticacao**: Dados para autenticação de usuário.
- **DadosToken**: Dados para o token JWT.

## Resumo

Esta Api está sendo desenvolvida com o objetivo de estudar e aprimorar as habilidades 
de desenvolvimento back-end com Java. 
O contexto da proposta é um uma API de um sistema de gerenciamento de clinica médica. 
O objetivo é desenvolver um MVP de uma API que possa responder as 
requisições minímas necessárias para a aplicação. 
Ainda a diversas melhorias e funcionalidades a serem implementadas.


