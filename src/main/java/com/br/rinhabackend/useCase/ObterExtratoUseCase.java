package com.br.rinhabackend.useCase;

import com.br.rinhabackend.dto.ExtratoDTO;
import com.br.rinhabackend.dto.SaldoDTO;
import com.br.rinhabackend.dto.TransacoesDTO;
import com.br.rinhabackend.repositories.ClienteRepository;
import com.br.rinhabackend.repositories.TransacaoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ObterExtratoUseCase {

    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository;

    public Mono<ExtratoDTO>  execute(Integer id){
        Mono<SaldoDTO> cliente = clienteRepository
                .findById(id)
                .cache()
                .map(SaldoDTO::criarSaldo);

        Flux<TransacoesDTO> transacoes = transacaoRepository.buscarTransacoes(id)
                .map(TransacoesDTO::criarTransacao);

        return cliente.zipWith(transacoes.collectList(), ExtratoDTO::new);
    }

    public ObterExtratoUseCase(ClienteRepository clienteRepository, TransacaoRepository transacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
    }
}
