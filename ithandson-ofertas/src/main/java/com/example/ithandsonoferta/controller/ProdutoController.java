package com.example.ithandsonoferta.controller;

import com.example.ithandsonoferta.domain.mysql.Produto;
import com.example.ithandsonoferta.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {
//    private RestTemplate restTemplate;
    private ProdutoService produtoService;

    private static final String WALKTHROUGHJAVA_API = "http://localhost:8888/api/produto";
    public ProdutoController(ProdutoService produtoService) {
//        this.restTemplate = restTemplate;
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<?> saveOrUpdate(@RequestBody Produto produto) {
//        HttpEntity<Produto> request = new HttpEntity<>(produto);
//        Produto prod = restTemplate.postForObject(WALKTHROUGHJAVA_API, request, Produto.class);
        Produto prod = produtoService.saveOrUpdate(produto);
        return ResponseEntity.ok(prod);
    }
}
