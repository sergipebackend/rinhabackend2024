package com.br.rinhabackend.repositories;

import com.br.rinhabackend.model.Transacao;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface TransacaoRepository extends R2dbcRepository<Transacao, Integer> {

    @Query("SELECT * FROM transacoes WHERE cliente_id = :id ORDER BY realizada_em DESC LIMIT 10")
    Flux<Transacao> buscarTransacoes(@Param("id") Integer id);
}
