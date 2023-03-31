package com.example.walkthroughjava.controller;

import com.example.walkthroughjava.domain.Pessoa;
import com.example.walkthroughjava.exception.SistemaException;
import com.example.walkthroughjava.service.PessoaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/produto")
public class ProdutoPageController {

    public ProdutoPageController() {
    }

    @GetMapping
    public String home(Model model)  {
        return "produto";
    }
}
