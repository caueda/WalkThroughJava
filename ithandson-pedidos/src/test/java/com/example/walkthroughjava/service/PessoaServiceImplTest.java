package com.example.walkthroughjava.service;

import com.example.walkthroughjava.domain.Pessoa;
import com.example.walkthroughjava.exception.SistemaException;
import com.example.walkthroughjava.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceImplTest {
    @Mock
    private PessoaRepository pessoaRepository;

    private PessoaService pessoaService;

    @BeforeEach
    public void setUp() {
        this.pessoaService = new PessoaServiceImpl(pessoaRepository);
    }

    @Test
    void findById() {
        pessoaService.findById(1L);
        verify(pessoaRepository, times(1)).findById(anyLong());
    }

    @Test
    void findByName() {
        List<Pessoa> mockResult = Arrays.asList(new Pessoa(), new Pessoa(), new Pessoa());
        when(pessoaRepository.findPessoaByNomeContainsIgnoreCase(anyString())).thenReturn(mockResult);
        List<Pessoa> result = pessoaService.findByName("Charles");
        assertThat(result, hasSize(3));
    }

    @Test
    void saveOrUpdate_CpfAlreadySaved_ExceptionThrown() {
        Pessoa pessoa = Pessoa.builder()
                .nome("Charles")
                .sobrenome("Xavier")
                .cpf("999.999.999-99")
                .dataNascimento(LocalDate.now())
                .build();
        when(pessoaRepository.findByCpf(anyString())).thenReturn(Optional.of(pessoa));
        Exception exception = assertThrows(SistemaException.class, () -> {
            this.pessoaService.save(pessoa);
        });
        String expectedMessage = "O CPF informado já existe.";
        assertThat(expectedMessage, equalTo(exception.getMessage()));
    }

    @Test
    void saveOrUpdate_DataNascimentoNotInformed_ExceptionThrown() {
        Pessoa pessoa = Pessoa.builder()
                .nome("Charles")
                .sobrenome("Xavier")
                .cpf("999.999.999-99")
                .dataNascimento(null)
                .build();
        when(pessoaRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        Exception exception = assertThrows(SistemaException.class, () -> {
            this.pessoaService.save(pessoa);
        });
        String expectedMessage = "A data de nascimento é obrigatória.";
        assertThat(expectedMessage, equalTo(exception.getMessage()));
    }

    @Test
    void saveOrUpdate_FutureDataNascimento_ExceptionThrown() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Pessoa pessoa = Pessoa.builder()
                .nome("Charles")
                .sobrenome("Xavier")
                .cpf("999.999.999-99")
                .dataNascimento(tomorrow)
                .build();
        when(pessoaRepository.findByCpf(anyString())).thenReturn(Optional.empty());
        Exception exception = assertThrows(SistemaException.class, () -> {
            this.pessoaService.save(pessoa);
        });
        String expectedMessage = "A data de nascimento não pode ser uma data futura.";
        assertThat(expectedMessage, equalTo(exception.getMessage()));
    }

    @Test
    void delete() {
        Pessoa pessoa = Pessoa.builder()
                .id(1L)
                .nome("Charles")
                .sobrenome("Xavier")
                .cpf("999.999.999-99")
                .dataNascimento(LocalDate.of(1982,1,13))
                .build();
        pessoaService.delete(pessoa.getId());
        verify(pessoaRepository, times(1)).deleteById(anyLong());
    }

//    @Test
//    void findAll() {
//        Iterable<Pessoa> mockResult = Arrays.asList(new Pessoa(), new Pessoa(), new Pessoa());
//        when(pessoaRepository.findAll(Sort.by(Sort.Order.asc("nome"), Sort.Order.asc("sobrenome")))).thenReturn(mockResult);
//        List<Pessoa> result = pessoaService.findAll();
//        assertThat(result, hasSize(3));
//    }

    @Test
    void findByCpf() {
        pessoaService.findByCpf("111.111.111-11");
        verify(pessoaRepository, times(1)).findByCpf(anyString());
    }
}