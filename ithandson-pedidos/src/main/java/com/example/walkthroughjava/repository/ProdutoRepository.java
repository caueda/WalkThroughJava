package com.example.walkthroughjava.repository;

import com.example.walkthroughjava.domain.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {
    List<Produto> findAllByNomeContainsIgnoreCase(String nome);
    @Query("SELECT p FROM Produto p ORDER BY p.nome")
    List<Produto> findAllOrderByNome();
}
