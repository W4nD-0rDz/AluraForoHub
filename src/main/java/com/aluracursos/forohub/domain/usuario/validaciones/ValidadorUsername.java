package com.aluracursos.forohub.domain.usuario.validaciones;

import com.aluracursos.forohub.domain.autor.DatosAltaAutor;
import com.aluracursos.forohub.domain.autor.validaciones.ValidadorDeAutor;
import com.aluracursos.forohub.domain.usuario.DatosAltaUsuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorUsername implements ValidadorDeUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(DatosAltaUsuario datos) {
        boolean usuarioExiste = usuarioRepository.existsByUsername(datos.username());
        if(usuarioExiste){
            throw new ValidationException("Ya existe un username igual en la base de datos.");
        }
    }
}
