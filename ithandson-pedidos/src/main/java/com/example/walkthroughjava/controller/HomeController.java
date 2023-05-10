package com.example.walkthroughjava.controller;

import com.example.walkthroughjava.domain.Pessoa;
import com.example.walkthroughjava.exception.SistemaException;
import com.example.walkthroughjava.service.PessoaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/home")
public class HomeController {

    private static final String CHUCK_NORRIS_JOKE_API = "https://api.chucknorris.io/jokes/random";
    private RestTemplate restTemplate;

    private PessoaService pessoaService;

    public HomeController(RestTemplate restTemplate, PessoaService pessoaService) {
        this.restTemplate = restTemplate;
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public String home(Model model) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        ResponseEntity<String> response = restTemplate.getForEntity(CHUCK_NORRIS_JOKE_API, String.class);
        var value = new String(response.getBody().getBytes());
        log.info(value);
        var pessoas = pessoaService.findAll(Pageable.ofSize(10));
        model.addAttribute("mensagem", value);
        model.addAttribute("pessoas", pessoas);
        model.addAttribute("pessoa", new Pessoa());
        return "home";
    }

    @PostMapping
    public String cadastrar(@Valid Pessoa pessoa, Errors errors) {
        log.info("Pessoa sendo cadastrada: {}", pessoa);
        try {
            this.pessoaService.save(pessoa);
        } catch (SistemaException e) {
            errors.rejectValue(e.getCampo(), e.getCampo(), e.getMessage());
        }
        if(errors.hasErrors()) {
            return "/home";
        }
        return "redirect:/home";
    }
}
