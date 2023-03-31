package com.example.walkthroughjava;

import com.example.walkthroughjava.repository.ProdutoRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

//@Disabled
@SpringBootTest
class DatabaseInitializeRunnerTest {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void produtoPringlesShouldExist() {
        var pringles = produtoRepository.findAllByNomeContainsIgnoreCase("pringles");
        assertThat( "Deve haver 1 produto Pringles",pringles, hasSize(1));
        assertThat("O nome do produto deve ser Pringles", pringles.get(0).getNome(), equalTo("Pringles"));
        assertThat("A descrição é diferente da esperada", pringles.get(0).getDescricao(), equalTo("Batata Pringles"));
        assertThat("O preço é diferente do esperado", pringles.get(0).getPreco(), equalTo(18.0));
    }

    @Test
    public void produtoDoritosShouldExist() {
        var pringles = produtoRepository.findAllByNomeContainsIgnoreCase("doritos");
        assertThat( "Deve haver 1 produto Doritos",pringles, hasSize(1));
        assertThat("O nome do produto deve ser Doritos", pringles.get(0).getNome(), equalTo("Doritos"));
        assertThat("A descrição é diferente da esperada", pringles.get(0).getDescricao(), equalTo("Doritos Cheddar"));
        assertThat("O preço é diferente do esperado", pringles.get(0).getPreco(), equalTo(14.0));
    }
}