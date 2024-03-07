package com.br.rinhabackend.dto;

import com.br.rinhabackend.model.Transacao;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public record TransacoesDTO(Long valor, String tipo, String descricao, ZonedDateTime realizada_em) {

    public static TransacoesDTO criarTransacao(Transacao t){
        return new TransacoesDTO(t.valor(), t.tipo(), t.descricao(), t.realizada_em().atZoneSameInstant(ZoneOffset.UTC));
    }
}
