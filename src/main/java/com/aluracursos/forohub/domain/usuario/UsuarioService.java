package com.aluracursos.forohub.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(DatosAltaUsuario datos) {
        Usuario usuario = new Usuario(datos);
        return usuarioRepository.save(usuario);
    }
}
