package com.br.rinhabackend.repositories;

import com.br.rinhabackend.dto.ExtratoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;


@Repository
public class CustomTransacaoRepository {

    @Autowired
    private final DatabaseClient databaseClient;

    public CustomTransacaoRepository(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public Mono<ExtratoDTO> buscarTransacoes(Integer id){

        String SQL = """ 
                SELECT t.id transacaoId ,c.saldo, c.limite, t.valor, t.tipo, t.descricao, t.realizada_em
                FROM transacoes t INNER JOIN clientes c on t.cliente_id = c.id  
                WHERE c.id = :id ORDER BY t.realizada_em DESC LIMIT 10
        """;

        return databaseClient.sql(SQL)
                .bind("id", id)
                .fetch()
                .all()
                .collectList()
                .map(ExtratoDTO::fromRows);
    }
}
