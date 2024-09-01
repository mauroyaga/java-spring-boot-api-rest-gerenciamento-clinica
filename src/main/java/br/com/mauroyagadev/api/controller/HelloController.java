package br.com.mauroyagadev.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
public class HelloController {

    //Este método será chamado quando a URL http://localhost:8080/hello for acessada
    //O método retorna um getString com a mensagem "Hello, World!"
    @GetMapping
    public String hello() {
        return "Olá Mundo!";
    }
}
