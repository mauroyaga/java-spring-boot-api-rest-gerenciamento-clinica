package br.com.mauroyagadev.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEnderecoMedico(
        @NotBlank //Anotation que indica que o atributo não pode ser nulo ou vazio.
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}") //anotação que indica que o atributo deve seguir o padrão de 8 dígitos.
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        String complemento,
        String numero) {





}
