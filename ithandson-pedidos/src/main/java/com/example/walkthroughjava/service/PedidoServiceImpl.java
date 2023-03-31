package com.example.walkthroughjava.service;

import com.example.walkthroughjava.domain.Pedido;
import com.example.walkthroughjava.domain.dto.ResumoPedidos;
import com.example.walkthroughjava.repository.PedidoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> findAllByPessoaCpf(String cpf) {
        List<Pedido> listaPedidos =  this.pedidoRepository.findAllByPessoaCpf(cpf);
        return listaPedidos;
    }
    @Override
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    @Override
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public List<ResumoPedidos> resumoPedidos() {
        return pedidoRepository.resumoPedidos();
    }
}
