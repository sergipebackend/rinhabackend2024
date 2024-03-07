package com.br.rinhabackend.exceptions;

public class EntidadeNaoEncontradaException extends UnprocessableEntity {
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
