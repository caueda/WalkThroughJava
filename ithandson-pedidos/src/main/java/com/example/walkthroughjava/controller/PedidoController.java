package com.example.walkthroughjava.controller;

import com.example.walkthroughjava.domain.Pedido;
import com.example.walkthroughjava.domain.dto.ResumoPedidos;
import com.example.walkthroughjava.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<List<Pedido>> findAllByPessoaCpf(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok(pedidoService.findAllByPessoaCpf(cpf));
    }

    @GetMapping("/resumo")
    public ResponseEntity<List<ResumoPedidos>> resumoPedidos() {
        List<ResumoPedidos> resumoPedidos = pedidoService.resumoPedidos();
        return ResponseEntity.ok(resumoPedidos);
    }

    @PostMapping
    public ResponseEntity<Pedido> findAllByName(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.save(pedido));
    }
}
