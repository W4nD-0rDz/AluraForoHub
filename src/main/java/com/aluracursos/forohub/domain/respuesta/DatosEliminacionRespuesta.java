package com.aluracursos.forohub.domain.respuesta;

import jakarta.validation.constraints.NotNull;

public record DatosEliminacionRespuesta(

        @NotNull
        Long respuestaId,

        @NotNull
        Long autorId
) {
}
