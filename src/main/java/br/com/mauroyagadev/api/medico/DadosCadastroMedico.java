package br.com.mauroyagadev.api.medico;

import br.com.mauroyagadev.api.endereco.DadosEnderecoMedico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;

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

public record DadosCadastroMedico(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotNull
        Especialidade especialidade,
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        @Valid
        DadosEnderecoMedico endereco) {
}
