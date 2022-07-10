package com.example.walkthroughjava.service;

import com.example.walkthroughjava.domain.Pessoa;
import com.example.walkthroughjava.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return this.pessoaRepository.findById(id);
    }

    @Override
    public List<Pessoa> findByName(String nome) {
        return this.pessoaRepository.findPessoaByNomeContainsIgnoreCase(nome);
    }

    @Override
    public Pessoa saveOrUpdate(Pessoa pessoa) {
        return this.pessoaRepository.save(pessoa);
    }

    @Override
    public void delete(Long id) {
        this.pessoaRepository.deleteById(id);
    }
}
