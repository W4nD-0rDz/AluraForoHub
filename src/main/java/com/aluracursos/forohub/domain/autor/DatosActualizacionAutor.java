package com.aluracursos.forohub.domain.autor;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record DatosActualizacionAutor(
        @NotNull
        Long id,

        @JsonProperty("password")
        @NotBlank(message = "Debes ingresar una clave de ingreso para proteger tus datos.")
        @Pattern(regexp ="^(?=.*[A-Za-z])(?=.*[\\p{P}\\p{S}])[A-Za-z\\d\\p{P}\\p{S}]{8,12}$",
         message ="La clave de ingreso debe contener entre 8 y 12 caracteres, incluir letras, números, signos de puntuación o símbolos matemáticos")
        String password,

        @JsonProperty("nombreFantasia")
                String nombreFantasia
        )
{
}
