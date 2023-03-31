package com.example.walkthroughjava.service;

import com.example.walkthroughjava.domain.Pedido;
import com.example.walkthroughjava.domain.dto.ResumoPedidos;

import java.util.List;

public interface PedidoService {
    List<Pedido> findAllByPessoaCpf(String cpf);

    Pedido save(Pedido pedido);

    void delete(Long id);
    List<ResumoPedidos> resumoPedidos();
}
