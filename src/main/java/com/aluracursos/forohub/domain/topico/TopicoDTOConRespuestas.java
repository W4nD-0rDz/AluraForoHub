package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.respuesta.Respuesta;
import com.aluracursos.forohub.domain.respuesta.RespuestaDTORegistrada;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record TopicoDTOConRespuestas(@JsonProperty("id") Long idTopico,
                                     @JsonProperty("autor_id") String nombreFantasia,
                                     @JsonProperty("fechaDeCreacion") LocalDateTime fecha,
                                     @JsonProperty("titulo") String titulo,
                                     @JsonProperty("contenido") String contenido,
                                     @JsonProperty("respuestas") List<RespuestaDTORegistrada> respuestas
) {
    public TopicoDTOConRespuestas(Topico topico) {
        this(
                topico.getId(),
                topico.getAutor().getNombreFantasia(),
                topico.getFechaDeCreacion(),
                topico.getTitulo(),
                topico.getContenidoDelTopico(),
                topico.getRespuestas().stream().map(RespuestaDTORegistrada::new).toList()
        );
    }
}
