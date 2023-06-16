package com.example.ithandsonoferta.service;

import com.example.ithandsonoferta.domain.mysql.Produto;
import com.example.ithandsonoferta.repository.ProdutoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

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

    @Override
    public Long count(Produto produto) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return this.produtoRepository.count(Example.of(produto, matcher));
    }

    @Override
    public Page<Produto> findByExample(Produto produto, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return this.produtoRepository.findAll(Example.of(produto, matcher), pageable);
    }

    @Override
    public void deleteById(Long id) {
        this.produtoRepository.deleteById(id);
    }
}
