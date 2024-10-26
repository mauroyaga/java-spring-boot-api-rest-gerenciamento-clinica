package br.com.mauroyagadev.api.medico;

import br.com.mauroyagadev.api.endereco.Endereco;

public record DadosdetalhamentoMedico(Long id, String nome, String crm, String email, String telefone, Especialidade especialidade, Endereco endereco) {

    public DadosdetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEmail(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }

}
