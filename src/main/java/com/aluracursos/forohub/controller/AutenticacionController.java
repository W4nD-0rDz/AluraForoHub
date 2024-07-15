package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.autor.*;

import com.aluracursos.forohub.infra.security.DatosJWTToken;
import com.aluracursos.forohub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(summary = "Valide el login del usuario.", description = "", tags = {"autor", "post"})
    public ResponseEntity autenticarUsuario(
            @RequestBody @Valid DatosAutenticacionAutor datosAutenticacionAutor
    ) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacionAutor.username(),
                datosAutenticacionAutor.password());
        var usuarioAutenticado = authenticationManager.authenticate(authenticationToken);
        var jwtToken = tokenService.generarToken((Autor) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(jwtToken));
    }
}
