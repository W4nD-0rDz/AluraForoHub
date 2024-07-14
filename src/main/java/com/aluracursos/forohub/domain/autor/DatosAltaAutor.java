package com.aluracursos.forohub.domain.autor;

import com.aluracursos.forohub.domain.usuario.TipoDeUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosAltaAutor(

        @NotBlank(message = "El nombre de usuario es obligatorio.")
        String username,

        @JsonProperty("nombreFantasia")
        @NotBlank(message = "Tu username será tu marca de identidad en el foro.")
        String nombreFantasia,

        @JsonProperty("tipoDeUsuario")
        TipoDeUsuario tipoDeUsuario,

        @JsonProperty("email")
        @Email(message = "El formato de Email inválido.")
        @NotBlank(message = "Una dirección de correo es obligatoria para participar del foro.")
        String email,

        @JsonProperty("password")
        @NotBlank(message = "Debes ingresar una clave de ingreso para proteger tus datos.")
        @Pattern(regexp ="^(?=.*[A-Za-z])(?=.*[\\p{P}\\p{S}])[A-Za-z\\d\\p{P}\\p{S}]{8,12}$",
        message ="La clave de ingreso de contener entre 8 y 12 caracteres, incluir letras, números, signos de puntuación o símbolos matemáticos")
        String password
) {
}
