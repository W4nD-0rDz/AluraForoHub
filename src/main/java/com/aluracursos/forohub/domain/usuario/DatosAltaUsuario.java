package com.aluracursos.forohub.domain.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosAltaUsuario(
        @JsonProperty("username")
        @NotBlank(message = "El nombre de usuario es obligatorio.")
        String username,

        @JsonProperty("tipoDeUsuario")
        TipoDeUsuario tipoDeUsuario
)
{
}
