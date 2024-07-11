package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.respuesta.DatosGeneracionDeRespuesta;
import com.aluracursos.forohub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@ResponseBody
@RequestMapping("/topicos")
//@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoServicio;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    //@Operation(summary = "Registre un tópico en la base de datos.", description = "")
    //tags = {"topico", "post"})
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos){
        var response = topicoServicio.crearTopico(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    //@Operation(summary = "Obtener una lista de topicos")
    //tags = {"topico", "get"})
    public ResponseEntity<Page<TopicoDTOListado>> listarTopicos(@PageableDefault(page = 0, size = 10, sort = "id")
                                                                Pageable paginacion){
        Page<Topico> paginaTopicos =  topicoRepository.findAll(paginacion);
        Page<TopicoDTOListado> paginaTopicoDTOListado = paginaTopicos.map(TopicoDTOListado::new);
        return ResponseEntity.ok(paginaTopicoDTOListado);
    }

    @GetMapping("/{id}")
    //@Operation(summary = "Obtenga los datos de un tópico específico por ID")
    //tags = {"topico", "get"})
    public ResponseEntity<TopicoDTORegistrado> mostrarTopicoporId(@PathVariable Long id){
        Topico topico = topicoRepository.findReferenceById(id);
        TopicoDTORegistrado  topicoRegistrado = new TopicoDTORegistrado(topico);
        return ResponseEntity.ok(topicoRegistrado);
    }

    @PutMapping("/{topicoId}/respuestas")
    @Transactional
//    //@Operation(summary = "Agrega respuestas a un tópico.")
//    //tags = {"topico", "put"})
    public ResponseEntity<TopicoDTOConRespuestas> agregarRespuestaATopico(
            @PathVariable Long topicoId,
            @RequestBody @Valid DatosGeneracionDeRespuesta datos) {
        topicoServicio.agregarRespuesta(topicoId, datos);
        Topico topico = topicoRepository.findReferenceById(topicoId);
        return ResponseEntity.ok(new TopicoDTOConRespuestas(topico));
    }

    @GetMapping("/{id}/respuestas")
    //@Operation(summary = "Obtenga los datos de un tópico específico por ID y sus respuestas")
    //tags = {"topico", "get"})
    public ResponseEntity<TopicoDTOConRespuestas> mostrarTopicoporIdConRespuestas(@PathVariable Long id){
        Topico topico = topicoRepository.findReferenceById(id);
        if (topico == null) {
            return ResponseEntity.notFound().build();
        }
        TopicoDTOConRespuestas  topicoConRespuestas = new TopicoDTOConRespuestas(topico);
        return ResponseEntity.ok(topicoConRespuestas);
    }

    @PutMapping("/{id}")
    //@Operation(summary = "Actualiza un tópico específico por ID")
    //tags = {"topico", "put"})
    public ResponseEntity<TopicoDTORegistrado> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos) {
        TopicoDTORegistrado topicoEditado = topicoServicio.actualizarTopico(id, datos);
        return ResponseEntity.ok(topicoEditado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    //@Operation(summary = "Elimine un tópico específico por ID y sus respuestas")
    //tags = {"topico", "delete"})
    public ResponseEntity borrarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos) {
        topicoServicio.borrarTopico(id, datos.autorId());
        return ResponseEntity.noContent().build();
    }
}