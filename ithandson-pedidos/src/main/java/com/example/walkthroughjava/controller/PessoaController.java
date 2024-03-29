package com.example.walkthroughjava.controller;

import com.example.walkthroughjava.domain.Pessoa;
import com.example.walkthroughjava.service.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable("id") Long id) {
        return ResponseEntity.of(pessoaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Pessoa>> findAllPageable(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(pessoaService.findAllPageable(paging));
    }

    @PostMapping("/example")
    public ResponseEntity<Page<Pessoa>> findAllPageableByExample(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size,
                                                        @RequestBody Pessoa pessoa) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(pessoaService.findByExample(pessoa, paging));
    }

    @PostMapping("/total")
    public ResponseEntity<Long> count(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.count(pessoa));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Pessoa pessoa) {
        this.pessoaService.save(pessoa);
        return ResponseEntity.ok(pessoa);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Pessoa pessoa) {
        this.pessoaService.update(pessoa);
        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        pessoaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
