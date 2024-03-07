package com.br.rinhabackend.repositories;

import com.br.rinhabackend.model.Cliente;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClienteRepository extends R2dbcRepository<Cliente, Integer> {

    @Query("UPDATE clientes SET saldo = (saldo + :valor) WHERE id = :id AND (saldo + :valor) > (-limite) RETURNING *")
    Mono<Cliente> buscarClienteAtualizado(Integer id, Long valor);
}
