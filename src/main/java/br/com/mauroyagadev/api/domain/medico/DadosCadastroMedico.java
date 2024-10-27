package br.com.mauroyagadev.api.domain.medico;

import br.com.mauroyagadev.api.domain.endereco.DadosEnderecoMedico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;




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
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        @Valid //Anotation do Bean Validation que indica que o atributo deve ser validado.
        DadosEnderecoMedico endereco) {
}
