package com.example.ithandsonoferta.controller;

import com.example.ithandsonoferta.domain.mysql.Produto;
import com.example.ithandsonoferta.service.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

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

    @PostMapping("/example")
    public ResponseEntity<Page<Produto>> findAllPageableByExample(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                  @RequestParam(value = "size", defaultValue = "10") int size,
                                                                  @RequestBody Produto produto) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(produtoService.findByExample(produto, paging));
    }

    @PostMapping("/total")
    public ResponseEntity<Long> count(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.count(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        produtoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
