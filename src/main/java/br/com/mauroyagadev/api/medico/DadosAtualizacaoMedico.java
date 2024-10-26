package br.com.mauroyagadev.api.medico;

import br.com.mauroyagadev.api.endereco.DadosEnderecoMedico;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEnderecoMedico endereco) {
}
