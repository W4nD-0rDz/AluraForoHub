package com.aluracursos.forohub.domain.autor.validaciones;

import com.aluracursos.forohub.domain.autor.DatosAltaAutor;
import com.aluracursos.forohub.domain.usuario.DatosAltaUsuario;

public interface ValidadorDeAutor {
    void validar(DatosAltaAutor datos);
}
