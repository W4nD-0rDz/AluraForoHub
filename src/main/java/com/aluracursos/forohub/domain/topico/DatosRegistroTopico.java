package com.aluracursos.forohub.domain.topico;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(

        @NotNull
        Long idAutor,

        @NotNull
        Long idCurso,

        @NotBlank
        String titulo,

        @NotBlank
        String mensaje
) {
}
