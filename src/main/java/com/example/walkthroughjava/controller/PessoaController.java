package com.example.walkthroughjava.controller;

import com.example.walkthroughjava.domain.Pessoa;
import com.example.walkthroughjava.service.PessoaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/{id}")
    public Pessoa findById(@PathVariable("id") Long id) {
        return pessoaService.findById(id)
                .orElse(new Pessoa());
    }
}
