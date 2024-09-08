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
* A classe é imutável, ou seja, após ser criada, seus atributos não podem ser alterados.
* */

public record DadosCadastroMedico(

        @NotBlank //Anotation do Bean Validation que indica que o atributo não pode ser nulo ou vazio.
        String nome,
        @NotBlank
        @Email //Anotation do Bean Validation que indica que o atributo deve ser um e-mail válido.
        String email,
        @NotBlank
        String telefone,
        @NotNull
        Especialidade especialidade,
        //Anotation do Bean Validation que indica que o atributo deve ter um formato específico.
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        @Valid //Anotation do Bean Validation que indica que o atributo deve ser validado.
        DadosEnderecoMedico endereco) {
}
