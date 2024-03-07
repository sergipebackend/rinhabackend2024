package com.br.rinhabackend.model;

import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;


@Table("transacoes")
public record Transacao(Integer cliente_id, Long valor, OffsetDateTime realizada_em, String descricao, String tipo) {

    public static Transacao newTransacao(Integer id, Long valor, String descricao, String tipo){
        return new Transacao(id, valor, null, descricao, tipo);
    }
}
