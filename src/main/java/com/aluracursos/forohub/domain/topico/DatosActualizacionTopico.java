package com.aluracursos.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(

        @NotNull
        Long autorId,

        Long idCurso,

        String titulo,

        String mensaje
) {
}
