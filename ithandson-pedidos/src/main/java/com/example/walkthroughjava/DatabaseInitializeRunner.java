package com.example.walkthroughjava;

import com.example.walkthroughjava.domain.Pessoa;
import com.example.walkthroughjava.domain.Produto;
import com.example.walkthroughjava.repository.PessoaRepository;
import com.example.walkthroughjava.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile("test")
public class DatabaseInitializeRunner implements CommandLineRunner {
    private PessoaRepository pessoaRepository;
    private ProdutoRepository produtoRepository;

    public DatabaseInitializeRunner(PessoaRepository pessoaRepository, ProdutoRepository produtoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Pessoa pessoa1 = Pessoa.builder()
                .cpf("999.999.999-99")
                .dataNascimento(LocalDate.parse("1982-01-13"))
                .nome("Charles")
                .sobrenome("Xavier")
                .build();
        pessoaRepository.save(pessoa1);

        Produto pringles = Produto.builder()
                .nome("Pringles")
                .descricao("Batata Pringles")
                .preco(18.0)
                .imageUrl("https://pt.openfoodfacts.org/images/products/003/700/032/0807/front_pt.8.full.jpg")
                .build();
        Produto doritos = Produto.builder()
                .nome("Doritos")
                .descricao("Doritos Cheddar")
                .imageUrl("https://world-pt.openfoodfacts.org/images/products/871/039/860/2039/front_fr.49.full.jpg")
                .preco(14.0)
                .build();

        produtoRepository.save(pringles);
        produtoRepository.save(doritos);
    }
}
