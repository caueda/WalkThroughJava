package com.example.ithandsonoferta.service;

import com.example.ithandsonoferta.domain.mongodb.Oferta;
import com.example.ithandsonoferta.enumerators.Situacao;

import java.util.List;
import java.util.Optional;

public interface OfertaService {
    List<Oferta> findAllBySituacao(Situacao situacao);

    Oferta saveOrUpdate(Oferta oferta);

    Optional<Oferta> findByProdutoIdAndSituacaoAtivo(String produtoId);
    public Optional<Oferta> inativarOfertaAtualCasoExista(Oferta oferta);
}
