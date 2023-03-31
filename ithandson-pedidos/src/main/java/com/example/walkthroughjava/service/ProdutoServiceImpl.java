package com.example.walkthroughjava.service;

import com.example.walkthroughjava.domain.Produto;
import com.example.walkthroughjava.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<Produto> findAllByNomeContainsIgnoreCase(String nome) {
        return produtoRepository.findAllByNomeContainsIgnoreCase(nome);
    }

    @Override
    public List<Produto> findAllOrderByNome() {
        List<Produto> listaProduto = new ArrayList<>();
        produtoRepository.findAllOrderByNome().forEach(listaProduto::add);
        return listaProduto;
    }

    @Override
    public Produto saveOrUpdate(Produto produto) {
        return this.produtoRepository.save(produto);
    }
}
