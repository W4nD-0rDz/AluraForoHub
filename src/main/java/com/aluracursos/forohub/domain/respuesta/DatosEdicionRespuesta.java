package com.aluracursos.forohub.domain.respuesta;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosEdicionRespuesta(

        @NotNull
        Long topicoId,

        @NotNull
        Long respuestaId,

        @NotNull
        Long autorId,

        @JsonProperty("titulo")
        String nuevoTtuloDeRespuesta,

        @NotBlank
        @JsonProperty("contenido")
        String nuevoContenidoDeRespuesta
)
{
}
