package br.com.mauroyagadev.api.controller;

import br.com.mauroyagadev.api.medico.DadosCadastroMedico;
import br.com.mauroyagadev.api.medico.Medico;
import br.com.mauroyagadev.api.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    //Injeção de dependência do repositório de médicos, com a anotation @Autowired.
    @Autowired
    private MedicoRepository repository;

    /*  O cadastro de um médico é feito através de uma requisição POST para o endpoint /medicos.
      O corpo da requisição deve conter um JSON com os dados do médico.
      Que estão sendo mapeados para o objeto DadosCadastroMedico.*/
    @PostMapping   //Anotation que indica que o método deve ser executado quando houver uma requisição POST para o endpoint /medicos.
    @Transactional //Anotation que indica que o método deve ser executado dentro de uma transação.
    public void cadastra(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));


    }


}
