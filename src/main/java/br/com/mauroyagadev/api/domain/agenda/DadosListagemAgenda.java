package br.com.mauroyagadev.api.domain.agenda;

import br.com.mauroyagadev.api.domain.medico.Medico;
import br.com.mauroyagadev.api.domain.paciente.Paciente;

public record DadosListagemAgenda(String dataHora, String descricao, String medicoNome, String pacienteNome) {

    public DadosListagemAgenda(Agenda agenda) {
        this(
                agenda.getDataHora().toString(),
                agenda.getDescricao(),
                agenda.getMedico().getNome(),
                agenda.getPaciente().getNome()
        );
    }
}