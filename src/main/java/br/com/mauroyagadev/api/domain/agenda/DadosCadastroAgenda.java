package br.com.mauroyagadev.api.domain.agenda;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroAgenda(

        @NotNull Long medicoId,
        @NotNull String medicoNome,
        @NotNull Long pacienteId,
        @NotNull String pacienteNome,
        @NotNull LocalDateTime dataHora,
        String descricao) {

}
