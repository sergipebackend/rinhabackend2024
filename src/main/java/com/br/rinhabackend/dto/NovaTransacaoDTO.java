package com.br.rinhabackend.dto;


import java.math.BigDecimal;

public record NovaTransacaoDTO(

        BigDecimal valor,
        String tipo,
        String descricao) {
}
