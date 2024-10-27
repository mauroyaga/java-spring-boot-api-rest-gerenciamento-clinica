package br.com.mauroyagadev.api.domain.agenda;

import br.com.mauroyagadev.api.domain.medico.Medico;
import br.com.mauroyagadev.api.domain.paciente.Paciente;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "agendas")
@Entity(name = "Agenda")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private LocalDateTime dataHora;

    private String descricao;



    public Agenda(@Valid DadosCadastroAgenda dados, Medico medico, Paciente paciente) {
        this.medico = medico;
        this.paciente = paciente;
        this.dataHora = dados.dataHora();
        this.descricao = dados.descricao();
    }

    public void AtualizarInformacoesAgenda(@Valid DadosAtualizacaoAgenda dados) {
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.dataHora() != null) {
            this.dataHora = LocalDateTime.parse(dados.dataHora());
        }
    }

}