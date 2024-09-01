package br.com.mauroyagadev.api.controller;

import br.com.mauroyagadev.api.medico.DadosCadastroMedico;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/medicos")
public class MedicoController {


  /*  O cadastro de um médico é feito através de uma requisição POST para o endpoint /medicos.
    O corpo da requisição deve conter um JSON com os dados do médico.
    Que estão sendo mapeados para o objeto DadosCadastroMedico.*/
    @PostMapping
    public void cadastra(@RequestBody DadosCadastroMedico dados) {
        System.out.println(dados);

    }


}
