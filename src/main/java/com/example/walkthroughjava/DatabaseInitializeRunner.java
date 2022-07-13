package com.example.walkthroughjava;

import com.example.walkthroughjava.domain.Pessoa;
import com.example.walkthroughjava.service.PessoaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile("test")
public class DatabaseInitializeRunner implements CommandLineRunner {
    private PessoaService pessoaService;

    public DatabaseInitializeRunner(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Override
    public void run(String... args) throws Exception {
        Pessoa pessoa1 = Pessoa.builder()
                .cpf("999.999.999-99")
                .dataNascimento(LocalDate.parse("1982-01-13"))
                .nome("Charles")
                .sobrenome("Xavier")
                .build();
        pessoaService.saveOrUpdate(pessoa1);
    }
}
