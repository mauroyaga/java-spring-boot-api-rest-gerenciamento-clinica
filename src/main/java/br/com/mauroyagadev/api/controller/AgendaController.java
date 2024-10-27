package br.com.mauroyagadev.api.controller;

import br.com.mauroyagadev.api.domain.agenda.*;
import br.com.mauroyagadev.api.domain.medico.Medico;
import br.com.mauroyagadev.api.domain.medico.MedicoRepository;
import br.com.mauroyagadev.api.domain.paciente.Paciente;
import br.com.mauroyagadev.api.domain.paciente.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

    @Autowired
    private AgendaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAgenda dados, UriComponentsBuilder uriBuilder) {
        Medico medico = medicoRepository.findById(dados.medicoId()).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        Paciente paciente = pacienteRepository.findById(dados.pacienteId()).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        var agenda = new Agenda(dados, medico, paciente);
        repository.save(agenda);

        var uri = uriBuilder.path("/agenda/{id}").buildAndExpand(agenda.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAgenda(agenda));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAgenda>> listar(Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemAgenda::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoAgenda dados) {
        var agenda = repository.getReferenceById(dados.id());
        agenda.AtualizarInformacoesAgenda(dados);
        return ResponseEntity.ok(new DadosDetalhamentoAgenda(agenda));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
       repository.deleteById(id);
       return ResponseEntity.noContent().build();
    }
}