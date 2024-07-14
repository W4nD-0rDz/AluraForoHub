package com.aluracursos.forohub.domain.autor;

import com.aluracursos.forohub.domain.usuario.DatosAltaUsuario;
import com.aluracursos.forohub.domain.usuario.TipoDeUsuario;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import com.aluracursos.forohub.domain.usuario.validaciones.ValidadorDeUsuario;
import com.aluracursos.forohub.domain.autor.validaciones.ValidadorDeAutor;
import com.aluracursos.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<ValidadorDeUsuario> validadoresU;

    @Autowired
    List<ValidadorDeAutor> validadoresA;

    public AutorDTOActualizado crearAutor(DatosAltaAutor datos) {

        DatosAltaUsuario datosUsuario = new DatosAltaUsuario(datos.username(), datos.tipoDeUsuario());
        validadoresU.forEach(v->v.validar(datosUsuario));
        Usuario usuario = new Usuario(datosUsuario);
        usuarioRepository.save(usuario);

        validadoresA.forEach(v->v.validar(datos));
        Autor autor = new Autor(datos);
        autor.setUsuario(usuario);
        autorRepository.save(autor);
        return new AutorDTOActualizado(autor);
    }

    public AutorDTOActualizado promoverAModerador(Long idAutor) {
        if(autorRepository.findById(idAutor).isEmpty()){
            throw new ValidacionDeIntegridad("El autor no se encuentra en la base de datos");
        }
        var autor = autorRepository.findReferenceById(idAutor);

        if(autor.getListaDeRespuestas().size() > 1000){
            autor.getUsuario().cambiarTipoDeUsuario(TipoDeUsuario.MODERADOR);
        }
        usuarioRepository.save(autor.getUsuario());
        return new AutorDTOActualizado(autor);
    }

    public AutorDTOActualizado cambiarPassword(Long idAutor, String nuevaPassword) {
        if(autorRepository.findById(idAutor).isEmpty()){
            throw new ValidacionDeIntegridad("El autor no se encuentra en la base de datos");
        }
        var autor = autorRepository.findReferenceById(idAutor);
        autor.setPassword(nuevaPassword);

        autorRepository.save(autor);

        return new AutorDTOActualizado(autor);
    }


}
