package com.aluracursos.forohub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class UsuarioNoAutorizadoException extends RuntimeException{
    public UsuarioNoAutorizadoException(String message) {
        super(message);
    }
}
