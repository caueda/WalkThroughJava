package com.example.ithandsonoferta.controller;

import com.example.ithandsonoferta.domain.mongodb.Oferta;
import com.example.ithandsonoferta.enumerators.Situacao;
import com.example.ithandsonoferta.service.OfertaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/oferta")
public class OfertaController {
    private OfertaService ofertaService;

    public OfertaController(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    @GetMapping
    public ResponseEntity<List<Oferta>> findAll() {
        return ResponseEntity.of(Optional.ofNullable(ofertaService.findAllBySituacao(Situacao.ATIVO)));
    }

    @PostMapping
    public ResponseEntity<?> saveOrUpdate(@RequestBody Oferta oferta) {
        this.ofertaService.saveOrUpdate(oferta);
        return ResponseEntity.ok(oferta);
    }
}
