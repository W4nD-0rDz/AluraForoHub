package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.autor.AutorRepository;
import com.aluracursos.forohub.domain.autor.AutorService;
import com.aluracursos.forohub.domain.autor.DatosAltaAutor;
import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.domain.topico.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorServicio;

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    @Transactional
    //@Operation(summary = "Registre un autor en la base de datos.", description = "")
    //tags = {"autor", "post"})
    public ResponseEntity registrarAutor(@RequestBody @Valid DatosAltaAutor datos) {
        var response = autorServicio.crearAutor(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
