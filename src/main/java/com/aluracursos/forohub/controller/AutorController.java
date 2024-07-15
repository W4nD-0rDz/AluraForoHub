package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.autor.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearer-key")
public class AutorController {

    @Autowired
    private AutorService autorServicio;

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping("/autor")
    @Transactional
    @Operation(summary = "Registre un autor en la base de datos.", description = "", tags = {"autor", "post"})
    public ResponseEntity registrarAutor(@RequestBody @Valid DatosAltaAutor datos) {
        var response = autorServicio.crearAutor(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/autores")
    @Operation(summary = "Obtener una lista de autores", tags = {"autor", "get"})
    public ResponseEntity<Page<AutorDTOListado>> listarAutores(@PageableDefault(page = 0, size = 20, sort = "id")
                                                               Pageable paginacion) {
        Page<Autor> paginaAutores = autorRepository.findAll(paginacion);
        Page<AutorDTOListado> paginaAutorDTOListado = paginaAutores.map(AutorDTOListado::new);
        return ResponseEntity.ok(paginaAutorDTOListado);
    }
}
