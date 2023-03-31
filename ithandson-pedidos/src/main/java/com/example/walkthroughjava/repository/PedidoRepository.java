package com.example.walkthroughjava.repository;

import com.example.walkthroughjava.domain.Pedido;
import com.example.walkthroughjava.domain.Pessoa;
import com.example.walkthroughjava.domain.dto.ResumoPedidos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Long> {
    @Query("SELECT p FROM Pedido p WHERE p.pessoa.id=:id ORDER BY p.dataPedido")
    List<Pedido> findAllByPessoaCpf(@Param("id") String cpf);

    @Query("SELECT " +
            "new com.example.walkthroughjava.domain.dto.ResumoPedidos(produto.nome, sum(pedido.quantidade), sum(pedido.quantidade * produto.preco)) " +
            "FROM Pedido pedido JOIN pedido.produto produto GROUP BY produto.nome")
    List<ResumoPedidos> resumoPedidos();
}
