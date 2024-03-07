package com.br.rinhabackend.dto;

import com.br.rinhabackend.model.Cliente;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

public record ExtratoDTO(SaldoDTO saldo, List<TransacoesDTO> ultimas_transacoes) {

    public static ExtratoDTO fromRows(List<Map<String, Object>> rows) {
        if(rows == null || rows.isEmpty()) return null;

        long limite = (long) (int) rows.get(0).get("limite");
        long saldo = (long) (int) rows.get(0).get("saldo");

        var ultimasTransacoes = rows.stream()
                .map(row -> new TransacoesDTO((long) (int) row.get("valor"),
                                        (String) row.get("tipo"),
                                        (String) row.get("descricao"),
                                        ((OffsetDateTime) row.get("realizada_em")).atZoneSameInstant(ZoneOffset.UTC))).toList();



        return new ExtratoDTO(SaldoDTO.criarSaldo(null), ultimasTransacoes);
    }
}