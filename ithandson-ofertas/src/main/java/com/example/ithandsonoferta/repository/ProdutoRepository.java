package com.example.ithandsonoferta.repository;

import com.example.ithandsonoferta.domain.mysql.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {
    List<Produto> findAllByNomeContainsIgnoreCase(String nome);

    @Query("SELECT p FROM Produto p ORDER BY p.nome")
    List<Produto> findAllOrderByNome();
}
