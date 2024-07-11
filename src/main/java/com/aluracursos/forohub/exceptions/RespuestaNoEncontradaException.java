package com.aluracursos.forohub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RespuestaNoEncontradaException extends RuntimeException{
    public RespuestaNoEncontradaException(String message) {
        super(message);
    }
}
