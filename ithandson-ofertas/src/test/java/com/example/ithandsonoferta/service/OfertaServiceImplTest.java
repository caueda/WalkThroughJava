package com.example.ithandsonoferta.service;

import com.example.ithandsonoferta.domain.mongodb.Oferta;
import com.example.ithandsonoferta.enumerators.Situacao;
import com.example.ithandsonoferta.repository.OfertaRepository;
import io.vavr.API;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OfertaServiceImplTest {

    @Mock
    private OfertaRepository ofertaRepository;

    private OfertaService ofertaService;

    @BeforeEach
    public void setUp() {
        this.ofertaService = new OfertaServiceImpl(ofertaRepository);
    }

    List<Oferta> ofertasMock = List.of(
            Oferta.builder()
                    .id("key1")
                    .desconto(10d)
                    .situacao(Situacao.ATIVO)
                    .produtoId("1L")
                    .build(),
            Oferta.builder()
                    .id("key2")
                    .desconto(5d)
                    .situacao(Situacao.ATIVO)
                    .produtoId("2L")
                    .build()
    );

    @Test
    void findAllBySituacao() {
        var situacaoAPesquisar = Situacao.INATIVO;
        this.ofertaService.findAllBySituacao(situacaoAPesquisar);
    }

    @Test
    void inativarOfertaAtualCasoExista() {
        var ofertaToSave = Oferta.builder()
                .id("key1")
                .desconto(10d)
                .situacao(Situacao.ATIVO)
                .produtoId("1L")
                .build();
        when(ofertaRepository.findByProdutoIdAndSituacao(anyString(), eq(Situacao.ATIVO))).thenReturn(
                Optional.ofNullable(ofertasMock.get(0))
        );
        var ofertaAtual = this.ofertaService.inativarOfertaAtualCasoExista(ofertaToSave);
        assertEquals(ofertaAtual.get().getSituacao(), Situacao.INATIVO);
    }

    @Test
    void saveOrUpdate() {
        var ofertaToSave = Oferta.builder()
                .id("key1")
                .desconto(10d)
                .situacao(Situacao.ATIVO)
                .produtoId("1L")
                .build();
        when(ofertaRepository.findByProdutoIdAndSituacao(anyString(), eq(Situacao.ATIVO))).thenReturn(
                Optional.ofNullable(ofertasMock.get(0))
        );
        this.ofertaService.saveOrUpdate(ofertaToSave);
        verify(ofertaRepository, times(2)).save(any(Oferta.class));
    }

    @Test
    void findByProdutoIdAndSituacaoAtivo() {
        this.ofertaService.findByProdutoIdAndSituacaoAtivo("key1");
        verify(ofertaRepository).findByProdutoIdAndSituacao(anyString(), eq(Situacao.ATIVO));
    }
}