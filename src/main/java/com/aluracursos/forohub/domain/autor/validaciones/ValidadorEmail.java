package com.aluracursos.forohub.domain.autor.validaciones;

import com.aluracursos.forohub.domain.autor.AutorRepository;
import com.aluracursos.forohub.domain.autor.DatosAltaAutor;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorEmail implements ValidadorDeAutor {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public void validar(DatosAltaAutor datos) {
        boolean autorExiste = autorRepository.existsByEmail(datos.email());
        if(autorExiste){
            throw new ValidationException("Ya existe un Email igual en la base de datos.");
        }
    }
}
