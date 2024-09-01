package br.com.mauroyagadev.api.medico;

import br.com.mauroyagadev.api.endereco.DadosEnderecoMedico;

/*
* Estre record é uma classe que representa os dados de cadastro de um médico.
* Ela possui os seguintes atributos:
* nome: String - nome do médico
* email: String - email do médico
* especialidade: Especialidade - especialidade do médico
* crm: String - CRM do médico
* endereco: DadosEnderecoMedico - dados de endereço do médico
*
* A classe é imutável, ou seja, após ser criada, seus atributos não podem ser alterados.
* */

public record DadosCadastroMedico(String nome, String email,
                                  Especialidade especialidade,
                                  String crm, DadosEnderecoMedico endereco) {
}
