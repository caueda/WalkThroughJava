package com.example.ithandsonoferta.repository;

import com.example.ithandsonoferta.domain.mongodb.Oferta;
import com.example.ithandsonoferta.enumerators.Situacao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OfertaRepository extends MongoRepository<Oferta, String> {
    Optional<List<Oferta>> findAllBySituacao(Situacao situacao);

    Optional<Oferta> findByProdutoIdAndSituacao(String produtoId, Situacao situacao);
}
