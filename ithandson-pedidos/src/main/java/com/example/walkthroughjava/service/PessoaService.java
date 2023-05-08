package com.example.walkthroughjava.service;

import com.example.walkthroughjava.domain.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PessoaService {
    Optional<Pessoa> findById(Long id);

    List<Pessoa> findByName(String name);

    Page<Pessoa> findByExample(Pessoa pessoa, Pageable pageable);

    Pessoa saveOrUpdate(Pessoa pessoa);

    void delete(Long id);

    List<Pessoa> findAll(Pageable paging);

    Page<Pessoa> findAllPageable(Pageable pageable);

    Optional<Pessoa> findByCpf(String cpf);

    Long count(Pessoa pessoa);

}
