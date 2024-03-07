package com.br.rinhabackend.useCase;

import com.br.rinhabackend.dto.NovaTransacaoDTO;
import com.br.rinhabackend.dto.NovaTransacaoResponseDTO;
import com.br.rinhabackend.exceptions.UnprocessableEntity;

import com.br.rinhabackend.model.Transacao;

import com.br.rinhabackend.repositories.ClienteRepository;
import com.br.rinhabackend.repositories.TransacaoRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;


@Service
public class NovaTransacaoUseCase {

    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository;

    public NovaTransacaoUseCase(ClienteRepository clienteRepository, TransacaoRepository transacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public Mono<NovaTransacaoResponseDTO> execute(Integer id, NovaTransacaoDTO request){
        return  transacaoRepository
                .save(Transacao.newTransacao(id, request.valor().longValue(), request.descricao(), request.tipo()))
                .flatMap(transacao -> clienteRepository.findById(transacao.cliente_id())
                        .map(cliente -> new NovaTransacaoResponseDTO(cliente.limite(), cliente.saldo())))
                .onErrorMap(DataIntegrityViolationException.class, ex -> new UnprocessableEntity("Limite insuficiente"));
    }

}
