package com.example.ithandsonoferta.service;

import com.example.ithandsonoferta.domain.mongodb.Oferta;
import com.example.ithandsonoferta.enumerators.Situacao;
import com.example.ithandsonoferta.repository.OfertaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OfertaServiceImpl implements OfertaService {
    private OfertaRepository ofertaRepository;

    public OfertaServiceImpl(OfertaRepository perfilRepository) {
        this.ofertaRepository = perfilRepository;
    }

    @Override
    public List<Oferta> findAllBySituacao(Situacao situacao) {
        List<Oferta> ofertas = new ArrayList<>();
        this.ofertaRepository.findAllBySituacao(situacao)
                .orElse(Collections.emptyList()).forEach(ofertas::add);
        return ofertas;
    }

    @Override
    public Oferta saveOrUpdate(Oferta oferta) {
        inativarOfertaAtualCasoExista(oferta);
        oferta.setSituacao(Situacao.ATIVO);
        return this.ofertaRepository.save(oferta);
    }

    public Optional<Oferta> inativarOfertaAtualCasoExista(Oferta oferta) {
        var ofertaAtual = findByProdutoIdAndSituacaoAtivo(oferta.getProdutoId()).map(ofertaCadastrada -> {
            if(!Objects.isNull(ofertaCadastrada)) {
                ofertaCadastrada.setSituacao(Situacao.INATIVO);
                this.ofertaRepository.save(ofertaCadastrada);
            }
            return ofertaCadastrada;
        });
        return ofertaAtual;
    }

    @Override
    public Optional<Oferta> findByProdutoIdAndSituacaoAtivo(String produtoId) {
        return this.ofertaRepository.findByProdutoIdAndSituacao(produtoId, Situacao.ATIVO);
    }
}
