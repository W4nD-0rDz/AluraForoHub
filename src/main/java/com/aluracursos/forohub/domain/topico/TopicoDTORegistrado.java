package com.aluracursos.forohub.domain.topico;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;

public record TopicoDTORegistrado(
        @JsonAlias("id") Long idTopico,
        @JsonAlias("nombreAutor") String nombreAutor,
        @JsonAlias("fechaDeCreacion") LocalDateTime fecha,
        @JsonAlias("titulo") String titulo,
        @JsonAlias("contenido") String contenido
) {
    public TopicoDTORegistrado(Topico topico) {
        this(
                topico.getId(),
                topico.getAutor().getNombreFantasia(),
                topico.getFechaDeCreacion(),
                topico.getTitulo(),
                topico.getContenidoDelTopico()
        );
    }
}
