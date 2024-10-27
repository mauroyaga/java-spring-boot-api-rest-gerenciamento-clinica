package br.com.mauroyagadev.api.domain.paciente;

import br.com.mauroyagadev.api.domain.endereco.DadosEnderecoPaciente;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        DadosEnderecoPaciente endereco) {
}
