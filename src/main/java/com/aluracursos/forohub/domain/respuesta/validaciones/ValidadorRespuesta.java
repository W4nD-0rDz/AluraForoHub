package com.aluracursos.forohub.domain.respuesta.validaciones;

import com.aluracursos.forohub.domain.respuesta.DatosGeneracionDeRespuesta;
import com.aluracursos.forohub.domain.respuesta.RespuestaRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorRespuesta implements ValidadorDeRespuesta{

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Override
    public void validar(DatosGeneracionDeRespuesta datos) {
        boolean respuestaExiste = respuestaRepository.existsByContenido(datos.contenidoRespuesta());
        if(respuestaExiste){
            throw new ValidationException("Ya existe una respuesta igual en el foro.");
        }
    }
}
