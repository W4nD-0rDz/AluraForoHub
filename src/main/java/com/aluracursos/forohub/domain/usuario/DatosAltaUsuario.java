package com.aluracursos.forohub.domain.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAltaUsuario(
        @JsonProperty("email")
        @NotBlank(message = "El email no puede estar vacío.")
        @Email(message = "Debe proporcionar un email válido.")
        String email,

        @JsonProperty("tipoDeUsuario")
        TipoDeUsuario tipoDeUsuario
)
        {
        public DatosAltaUsuario(@JsonProperty("email") String email) {
                this(email, TipoDeUsuario.ESTUDIANTE);
        }
}
