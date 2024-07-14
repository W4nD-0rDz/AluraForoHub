package com.aluracursos.forohub.domain.topico.validaciones;

import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMensaje implements ValidadorDeTopico{

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DatosRegistroTopico datos) {
        boolean topicoExiste = topicoRepository.existsByContenidoDelTopico(datos.mensaje());
        if(topicoExiste){
            throw new ValidationException("Ya existe un tópico con la misma consulta.");
        }
    }
}
