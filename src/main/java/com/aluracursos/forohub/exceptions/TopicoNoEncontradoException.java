package com.aluracursos.forohub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TopicoNoEncontradoException extends RuntimeException{
    public TopicoNoEncontradoException(String message) {
        super(message);
    }
}
