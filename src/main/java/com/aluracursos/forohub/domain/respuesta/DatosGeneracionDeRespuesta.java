package com.aluracursos.forohub.domain.respuesta;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosGeneracionDeRespuesta(

        @NotNull
        Long autorId,

        @NotBlank
        String tituloRespuesta,

        @NotBlank
        String contenidoRespuesta
) {
}
