package com.example.walkthroughjava.service;

import com.example.walkthroughjava.domain.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaService {
    Optional<Pessoa> findById(Long id);

    List<Pessoa> findByName(String name);

    Pessoa saveOrUpdate(Pessoa pessoa);

    void delete(Long id);

    List<Pessoa> findAll();

    Optional<Pessoa> findByCpf(String cpf);

}
