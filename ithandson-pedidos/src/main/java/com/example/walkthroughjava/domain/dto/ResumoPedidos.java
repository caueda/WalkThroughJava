package com.example.walkthroughjava.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumoPedidos {
    private String produto;
    private Long quantidadeTotal;
    private Double precoTotal;
}
