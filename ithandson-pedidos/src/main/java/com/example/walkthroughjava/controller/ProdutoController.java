package com.example.walkthroughjava.controller;

import com.example.walkthroughjava.domain.Produto;
import com.example.walkthroughjava.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(produtoService.findAllOrderByNome());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<Produto>> findAllByName(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(produtoService.findAllByNomeContainsIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<?> saveOrUpdate(@RequestBody Produto produto) {
        this.produtoService.saveOrUpdate(produto);
        return ResponseEntity.ok(produto);
    }
}
