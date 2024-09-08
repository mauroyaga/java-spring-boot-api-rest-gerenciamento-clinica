# Projeto API Rest com Java Spring Boot

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)![SpingBoot](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

Está aplicação é uma API rest, Spring. Está sendo desenvolvido com o objetivos de estudos para o desenvolvimento back-end com Java.

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


---

## Dependências

---

## Instruções

## Banco Postgre com Docker

### Controle de versão com Flyway
é importante sempre parar o projeto ao criar os arquivos de migrations,
para evitar que o Flyway os execute antes da hora, com o código ainda
incompleto, causando com isso problemas.

Entretanto, eventualmente pode acontecer de esquecermos de parar o projeto
e algum erro acontecer ao tentar inicializar a aplicação.
 Nesse caso será exibido o seguinte erro ao tentar inicializar a aplicação:

```bash
Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'flywayInitializer' defined in class path resource [org/springframework/boot/autoconfigure/flyway/FlywayAutoConfiguration$FlywayConfiguration.class]: Validate failed: Migrations have failed validation
```
Perceba na mensagem de erro que é indicado que alguma migration falhou, impedindo assim que o projeto seja inicializado corretamente. Esse erro também pode acontecer se o código da migration estiver inválido, contendo algum trecho de SQL digitado de maneira incorreta.

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


