# Projeto API Rest com Java Spring Boot

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)![SpingBoot](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

Está aplicação é uma API Rest com Java e Spring Boot. Está sendo desenvolvida com o objetivos de estudos para aplicações back-end.

---


## Sumário

- [Projeto API Rest com Java Spring Boot](#projeto-api-rest-com-java-spring-boot)
  - [Sumário](#sumário)
  - [Descrição](#descrição)
  - [Tecnologias](#tecnologias)
  - [Dependências](#dependências)
  - [Instruções](#instruções)
    - [Banco Postgre com Docker](#banco-postgre-com-docker)
    - [Controle de versão com Flyway](#controle-de-versão-com-flyway)
  - [Documentação](#documentação)
    - [EndPoints](#endpoints)
    - [Validações](#validações)
  - [Abordagem](#abordagem)
    - [Arquitetura](#arquitetura)

---

## Descrição
Este projeto é uma API REST desenvolvida com Java e Spring Boot.
Ele foi criado com o objetivo de estudar e aprimorar as habilidades de
desenvolvimento back-end com Java.
O contexto da proposta é um uma API de um sistema de gerenciamento de clinica médica.
O objetivo é desenvolver um MVP de uma API que possa responder as requisições minímas necessárias para a aplicação.

---

## Tecnologias
- Java
- Spring Boot
- Postgre
- Docker

---

## Dependências

- Spring Web
- Spring Data JPA
- Spring Boot DevTools
- Lombok
- Spring Test
- Postgres Driver
- Flyway
- Jakarta Bean Validation

---

## Instruções

## Banco Postgre com Docker


Com o docker devidamente instalado em seu ambiente, no terminal, execute o seguinte comando
para iniciar uma instância do PostgreSQL:

```bash
  docker run --name postgres -e POSTGRES_PASSWORD=root -p 5432:5432 -d postgres
```
Se tudo ocorrer bem, você verá uma mensagem informando que o container foi criado com sucesso.
Para verificar se o container está rodando, execute o seguinte comando:

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
- Nome do banco de dados: api
- Usuário: root
- Senha: root

### Controle de versão com Flyway

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
drop database vollmed_api;
create database vollmed_api;
```

---


## Documentação

### EndPoints

#### Parametros de requisição

***Paginação***

***page***: Este parâmetro define o número da página que você deseja recuperar.
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

 A requisição a baixo retorna 1 resultado por página, da segunda página

```bash
localhost:8080/medicos?size=1&page=1
```

 A requisição a baixo retornar a lista de médicos,
 ordenados pelo campo nome em ordem ascendente por padrão.

 ```bash
 localhost:8080/medicos?sort=nome
```

### Validações
link para a documentação das validações, com o validation do Spring Boot

[https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#builtinconstraints](https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#builtinconstraints)

---

## Abordagem

### Arquitetura

Quando uma requisição é feita para a API, o fluxo dos dados ocorre da seguinte maneira:

- A requisição chega ao MedicoController. Este controlador tem um método cadastra
 que é mapeado para lidar com requisições POST para o endpoint /medicos.
- O corpo da requisição é automaticamente mapeado para um objeto DadosCadastroMedico pelo Spring,
graças à anotação @RequestBody. Este objeto é um record que contém os dados do médico que estão sendo enviados na requisição.
- O método cadastra então cria um novo objeto Medico usando os dados do objeto DadosCadastroMedico.
- Durante a criação do objeto Medico, um novo objeto Endereco também é criado usando
 os dados de endereço contidos no objeto DadosCadastroMedico.
- O objeto Medico é então passado para o método save do MedicoRepository,
que salva o médico no banco de dados.
- O MedicoRepository é uma interface que estende JpaRepository,
o que significa que ele herda uma série de métodos para trabalhar com o banco de dados, incluindo o método save. O Spring Data JPA fornece automaticamente uma implementação desta interface em tempo de execução.
- O objeto Medico salvo é então retornado pelo método save e
pode ser usado para qualquer processamento adicional necessário.


fluxo: requisição HTTP -> MedicoController -> DadosCadastroMedico -> Medico -> MedicoRepository -> banco de dados.


