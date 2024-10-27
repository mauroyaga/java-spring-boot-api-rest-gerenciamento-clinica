package br.com.mauroyagadev.api.domain.agenda;



public record DadosDetalhamentoAgenda(Long id, String dataHora, String descricao, Long medicoId, String medicoNome, Long pacienteId, String pacienteNome) {
    public DadosDetalhamentoAgenda(Agenda agenda) {
        this(
                agenda.getId(),
                agenda.getDataHora().toString(),
                agenda.getDescricao(),
                agenda.getMedico().getId(),
                agenda.getMedico().getNome(),
                agenda.getPaciente().getId(),
                agenda.getPaciente().getNome()
        );
    }
}