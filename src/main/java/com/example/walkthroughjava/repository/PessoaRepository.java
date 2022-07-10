package com.example.walkthroughjava.repository;

import com.example.walkthroughjava.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findPessoaByNomeContainsIgnoreCase(String nome);
}
