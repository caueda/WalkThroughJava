package com.example.ithandsonoferta.domain.mongodb;

import com.example.ithandsonoferta.enumerators.Situacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Oferta {
    @Id
    private String id;
    private String produtoId;
    private String pedidoUrl;
    private String imageProdutoUrl;
    private Double desconto;
    private String mensagem;
    private Situacao situacao;
}
