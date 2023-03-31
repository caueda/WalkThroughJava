package com.example.ithandsonoferta.controller;

import com.example.ithandsonoferta.domain.mongodb.Oferta;
import com.example.ithandsonoferta.enumerators.Situacao;
import com.example.ithandsonoferta.service.OfertaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OfertaControllerTest {

    @Mock
    private OfertaService ofertaService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper;

    @InjectMocks
    OfertaController ofertaController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ofertaController)
                .build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void findAll() throws Exception {
        List<Oferta> ofertasMock = List.of(Oferta.builder()
                        .id("key1")
                        .build(),
                Oferta.builder()
                        .id("key2")
                        .build());
        when(ofertaService.findAllBySituacao(any(Situacao.class))).thenReturn(ofertasMock);
        mockMvc.perform(get("/api/oferta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", equalTo("key1")))
                .andExpect(jsonPath("$.[1].id", equalTo("key2")));
    }

    @Test
    void saveOrUpdate() throws Exception {
        Oferta ofertaToSave = Oferta.builder()
                .id("ke1")
                .produtoId("1")
                .desconto(10.0)
                .mensagem("Oferta")
                .build();
        mockMvc.perform(post("/api/oferta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ofertaToSave)))
                .andExpect(status().isOk());
        verify(ofertaService).saveOrUpdate(ofertaToSave);
    }
}