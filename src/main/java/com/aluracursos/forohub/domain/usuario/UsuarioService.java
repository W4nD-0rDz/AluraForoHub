package com.aluracursos.forohub.domain.usuario;

import com.aluracursos.forohub.domain.usuario.validaciones.ValidadorDeUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<ValidadorDeUsuario> validadores;

    public Usuario crearUsuario(DatosAltaUsuario datos) {
        validadores.forEach(v->v.validar(datos));
        Usuario usuario = new Usuario(datos);
        return usuarioRepository.save(usuario);
    }
}
