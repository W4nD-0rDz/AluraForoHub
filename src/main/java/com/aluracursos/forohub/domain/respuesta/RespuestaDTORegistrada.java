package com.aluracursos.forohub.domain.respuesta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record RespuestaDTORegistrada(
        @JsonProperty("id") Long respuestaId,
        @JsonProperty("autor_id") Long autorId,
        @JsonProperty("fechaDeCreacion") LocalDateTime fecha,
        @JsonProperty("titulo") String titulo,
        @JsonProperty("contenido") String contenido
) {
    public RespuestaDTORegistrada(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getAutor().getId(),
                respuesta.getFechaDeCreacion(),
                respuesta.getTitulo(),
                respuesta.getContenido()
        );
    }
}