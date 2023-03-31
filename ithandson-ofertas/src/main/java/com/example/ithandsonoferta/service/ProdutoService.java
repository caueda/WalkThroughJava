package com.example.ithandsonoferta.service;

import com.example.ithandsonoferta.domain.mysql.Produto;

import java.util.List;

public interface ProdutoService {
    List<Produto> findAllByNomeContainsIgnoreCase(String nome);
    List<Produto> findAllOrderByNome();
    Produto saveOrUpdate(Produto produto);
}
