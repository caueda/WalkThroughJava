package com.example.walkthroughjava.service;

import com.example.walkthroughjava.domain.Pessoa;
import com.example.walkthroughjava.exception.SistemaException;
import com.example.walkthroughjava.repository.PessoaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        if(this.findByCpf(pessoa.getCpf()).isPresent()) {
            throw new SistemaException("cpf","O CPF informado já existe.");
        }
        if(Objects.isNull(pessoa.getDataNascimento())) {
            throw new SistemaException("dataNascimento", "A data de nascimento é obrigatória.");
        } else if(pessoa.getDataNascimento().isAfter(LocalDate.now())) {
            throw new SistemaException("dataNascimento", "A data de nascimento não pode ser uma data futura.");
        }
        return this.pessoaRepository.save(pessoa);
    }

    @Override
    public void delete(Long id) {
        this.pessoaRepository.deleteById(id);
    }

    @Override
    public List<Pessoa> findAll(Pageable paging) {
        Page<Pessoa> page = this.pessoaRepository.findAll(paging);
        List<Pessoa> pessoas = new ArrayList<>();
        page.forEach(pessoas::add);
        return pessoas;
    }

    @Override
    public Page<Pessoa> findAllPageable(Pageable paging) {
        return this.pessoaRepository.findAll(paging);
    }

    @Override
    public Optional<Pessoa> findByCpf(String cpf) {
        return this.pessoaRepository.findByCpf(cpf);
    }

    @Override
    public Long count() {
        return this.pessoaRepository.count();
    }
}
