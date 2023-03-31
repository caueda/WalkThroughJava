package com.example.walkthroughjava.service;

import com.example.walkthroughjava.domain.Produto;

import java.util.List;

public interface ProdutoService {
    List<Produto> findAllByNomeContainsIgnoreCase(String nome);
    List<Produto> findAllOrderByNome();
    Produto saveOrUpdate(Produto produto);
}
