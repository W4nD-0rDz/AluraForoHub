package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.respuesta.*;
import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.domain.topico.TopicoDTOConRespuestas;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/topicos/{topicoId}/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService servicio;


    @PutMapping("{respuestaId}")
    @Transactional
    //@Operation(summary = "Edite una respuesta existente en la base de datos.", description = "",
    //tags = {"respuesta", "put"})
    public ResponseEntity<RespuestaDTOEditada> actualizarRespuesta(
            @PathVariable Long topicoId,
            @RequestBody @Valid DatosEdicionRespuesta datos) {
        RespuestaDTOEditada respuestaEditada = servicio.editarRespuesta(topicoId, datos);
        return ResponseEntity.ok(respuestaEditada);
    }

    @DeleteMapping("{respuestaId}")
    @Transactional
    //Operation(summary = "Elimine una respuesta existente en la base de datos.")
    //tags = {"respuesta", "delete"})
    public ResponseEntity borrarRespuesta(
            @PathVariable Long topicoId,
            @RequestBody @Valid DatosEliminacionRespuesta datos

    ) {
        servicio.borrarRespuesta(topicoId, datos);
        return ResponseEntity.noContent().build();
    }

}
