package com.aluracursos.forohub.domain.respuesta.validaciones;

import com.aluracursos.forohub.domain.respuesta.DatosGeneracionDeRespuesta;
import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;

public interface ValidadorDeRespuesta {
    void validar(DatosGeneracionDeRespuesta datos);
}
