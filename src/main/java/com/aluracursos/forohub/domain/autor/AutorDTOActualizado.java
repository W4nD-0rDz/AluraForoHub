package com.aluracursos.forohub.domain.autor;

import com.aluracursos.forohub.domain.topico.Topico;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AutorDTOActualizado(
        @JsonProperty("nombreFantasia") String nombreFantasia,
        @JsonProperty("email") String email,
        @JsonProperty("username") String username,
        @JsonProperty("password") String password,
        @JsonProperty("tipoUsuario") String tipoUsuario

) {
    public AutorDTOActualizado(Autor autor) {
        this(
                autor.getNombreFantasia(),
                autor.getEmail(),
                autor.getUsuario().getUsername(),
                autor.getPassword(),
                autor.getUsuario().getTipoDeUsuario().toString()
        );
    }
}
