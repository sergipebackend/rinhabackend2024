package com.br.rinhabackend.dto;

import com.br.rinhabackend.model.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;


public record SaldoDTO(Long total, Long limite, @JsonProperty("data_extrato") ZonedDateTime dataExtrato){

    public static SaldoDTO criarSaldo(Cliente c){
        return new SaldoDTO(c.saldo(), c.limite(), OffsetDateTime.now().atZoneSameInstant(ZoneOffset.UTC));
    }
}
