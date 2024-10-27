package br.com.mauroyagadev.api.domain.agenda;

public record DadosAtualizacaoAgenda(
        Long id,
        String descricao,
        String dataHora
) {
}
