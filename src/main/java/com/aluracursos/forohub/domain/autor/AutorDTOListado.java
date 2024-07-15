package com.aluracursos.forohub.domain.autor;

import com.aluracursos.forohub.domain.topico.Status;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.usuario.TipoDeUsuario;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;

public record AutorDTOListado(
        @JsonAlias("username") String username,
        @JsonAlias("tipoDeUsuario") TipoDeUsuario tipoDeUsuario,
        @JsonAlias("email") String email,
        @JsonAlias("password") String password
) {
    public AutorDTOListado(Autor autor) {
        this(
                autor.getUsername(),
                autor.getUsuario().getTipoDeUsuario(),
                autor.getEmail(),
                autor.getPassword()
        );
    }

}
