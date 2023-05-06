package com.example.walkthroughjava.repository;

import com.example.walkthroughjava.domain.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findPessoaByNomeContainsIgnoreCase(String nome);
    Page<Pessoa> findAll(Pageable pageable);
    Optional<Pessoa> findByCpf(String cpf);
}
