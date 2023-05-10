package com.example.walkthroughjava.controller;

import com.example.walkthroughjava.config.ConfigTest;
import com.example.walkthroughjava.domain.Pessoa;
import com.example.walkthroughjava.exception.SistemaException;
import com.example.walkthroughjava.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigTest.class,loader = AnnotationConfigContextLoader.class)
public class PessoaControllerTest {

    @Mock
    private PessoaService pessoaService;

    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @InjectMocks
    PessoaController pessoaController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaController)
                .setControllerAdvice(CustomResponseEntityExceptionHandler.class)
                .build();
//        ReflectionTestUtils.setField(pessoaController, "property", "object");
    }

    @Test
    void findById() throws Exception {
        Pessoa charles = Pessoa.builder()
                .id(1L)
                .nome("Charles")
                .sobrenome("Xavier")
                .dataNascimento(LocalDate.of(1982, 01, 01))
                .cpf("111.111.111-11")
                .build();
        when(pessoaService.findById(anyLong())).thenReturn(Optional.of(charles));
        mockMvc.perform(get("/api/pessoa/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo("Charles")));
    }

    @Test
    void findAll() throws Exception {
        List<Pessoa> listPessoaMock = Arrays.asList(
                Pessoa.builder()
                        .id(1L)
                        .nome("Charles")
                        .sobrenome("Xavier")
                        .cpf("111.111.111-11")
                        .dataNascimento(LocalDate.of(1982, 1, 1))
                        .build(),
                Pessoa.builder()
                        .id(2L)
                        .nome("Logan")
                        .sobrenome("Wolverine")
                        .cpf("999.999.999-99")
                        .dataNascimento(LocalDate.of(1970, 1, 1))
                        .build());
        when(pessoaService.findAll()).thenReturn(listPessoaMock);
        mockMvc.perform(get("/api/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)));
    }

    @Test
    void saveOrUpdate_CpfAlreadSaved_throwException() throws Exception {
        Pessoa pessoa = Pessoa.builder()
                .id(1L)
                .nome("Charles")
                .sobrenome("Xavier")
                .cpf("111.111.111-11")
                .dataNascimento(LocalDate.of(1982, 1, 1))
                .build();
        when(pessoaService.save(any(Pessoa.class))).thenThrow(new SistemaException("cpf", "O CPF informado já existe."));
        var result = mockMvc.perform(post("/api/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoa)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", equalTo("O CPF informado já existe.")));
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(delete("/api/pessoa/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(pessoaService, times(1)).delete(1L);
    }
}