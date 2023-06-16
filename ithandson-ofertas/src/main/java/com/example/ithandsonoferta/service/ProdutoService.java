package com.example.ithandsonoferta.service;

import com.example.ithandsonoferta.domain.mysql.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoService {
    List<Produto> findAllByNomeContainsIgnoreCase(String nome);
    List<Produto> findAllOrderByNome();
    Produto saveOrUpdate(Produto produto);
    Long count(Produto produto);
    Page<Produto> findByExample(Produto produto, Pageable pageable);
    void deleteById(Long id);
}
