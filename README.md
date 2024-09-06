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
        -[Banco Postgre no Docker](#banco-postgre-no-docker)
  - [Documentação](#documentação)
        -[Validações](#validações)
  - [Abordagem](#abordagem)
        [Arquitetura](#arquitetura)

---

## Descrição
Este projeto é uma API REST desenvolvida com Java e Spring Boot.
Ele foi criado com o objetivo de estudar e aprimorar as habilidades de
desenvolvimento back-end com Java.
O contexto da proposta é um uma API de um sistema de gerenciamento de clinica médica.
O objetivo é uma api que possa responder as requisições da necessárias para a aplicação.


---

## Tecnologias


---

## Dependências

---

## Instruções

---


## Documentação

### Validações
link para a documentação das validações:
[Validações](https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#builtinconstraints

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


