package com.aluracursos.forohub.domain.topico;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;

public record TopicoDTOListado(
        @JsonAlias("nombreAutor") String nombreAutor,
        @JsonAlias("fechaDeCreacion") LocalDateTime fecha,
        @JsonAlias("titulo") String titulo,
        @JsonAlias("status") Status status
) {
    public TopicoDTOListado(Topico topico) {
        this(
                topico.getAutor().getNombreFantasia(),
                topico.getFechaDeCreacion(),
                topico.getTitulo(),
                topico.getStatus()
        );
    }
}
