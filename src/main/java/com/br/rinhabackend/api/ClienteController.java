package com.br.rinhabackend.api;

import com.br.rinhabackend.dto.*;
import com.br.rinhabackend.exceptions.EntidadeNaoEncontradaException;
import com.br.rinhabackend.exceptions.UnprocessableEntity;
import com.br.rinhabackend.useCase.NovaTransacaoUseCase;
import com.br.rinhabackend.useCase.ObterExtratoUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
public class ClienteController {

    private final NovaTransacaoUseCase novaTransacaoUseCase;
    private final ObterExtratoUseCase obterExtratoUseCase;

    @PostMapping(value = "clientes/{id}/transacoes")
    public Mono<NovaTransacaoResponseDTO> transacionar(@PathVariable Integer id, @RequestBody  NovaTransacaoDTO request){
        validarClienteId(id);
        validarPayload(request);
        return novaTransacaoUseCase.execute(id, request);
    }

    @GetMapping(value = "clientes/{id}/extrato")
    public Mono<ExtratoDTO> obterExtrato(@PathVariable Integer id){
        validarClienteId(id);
        return obterExtratoUseCase.execute(id);
    }

    private  void validarClienteId(Integer id) {
        if (id < 1 || id > 5) {
            throw  new EntidadeNaoEncontradaException("Cliente não encontrado");
        }
    }

    private void validarPayload(NovaTransacaoDTO request) {
        if ((request.valor() == null)
                || (request.valor().longValue() < 0)
                || (request.valor().scale() >  0)
                || (!List.of("c", "d").contains(request.tipo()))
                || (request.descricao() == null)
                || (request.descricao().isEmpty())
                || (request.descricao().length() > 10)) {
            throw new UnprocessableEntity("Payload inválido");
        }
    }

    public ClienteController(NovaTransacaoUseCase novaTransacaoUseCase, ObterExtratoUseCase obterExtratoUseCase) {
        this.novaTransacaoUseCase = novaTransacaoUseCase;
        this.obterExtratoUseCase = obterExtratoUseCase;
    }
}
