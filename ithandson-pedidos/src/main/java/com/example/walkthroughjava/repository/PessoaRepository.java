package com.example.walkthroughjava.repository;

import com.example.walkthroughjava.domain.Pessoa;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long> {
    List<Pessoa> findPessoaByNomeContainsIgnoreCase(String nome);
    List<Pessoa> findAll();

    Optional<Pessoa> findByCpf(String cpf);

}
