package com.aluracursos.forohub.domain.autor;

import com.aluracursos.forohub.domain.usuario.DatosAltaUsuario;
import com.aluracursos.forohub.domain.usuario.TipoDeUsuario;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AutorDTOActualizado crearAutor(DatosAltaAutor datos) {
        DatosAltaUsuario datosUsuario = new DatosAltaUsuario(datos.email(), TipoDeUsuario.ESTUDIANTE);
        Usuario usuario = new Usuario(datosUsuario);
        usuario = usuarioRepository.save(usuario);
        Autor autor = new Autor(datos);
        autor.setUsuario(usuario);
        autorRepository.save(autor);
        return new AutorDTOActualizado(autor);
    }

    public AutorDTOActualizado promoverAModerador(Long idAutor) {
//        if(autorRepository.findById(datos.idAutor()).isEmpty()){
//            throw new ValidacionDeIntegridad("El autor no se encuentra en la base de datos");
//        }
        var autor = autorRepository.findReferenceById(idAutor);
        autor.getUsuario().cambiarTipoDeUsuario(TipoDeUsuario.MODERADOR);

        usuarioRepository.save(autor.getUsuario());

        return new AutorDTOActualizado(autor);
    }

    public AutorDTOActualizado cambiarPassword(Long idAutor, String nuevaPassword) {
//        if(autorRepository.findById(datos.idAutor()).isEmpty()){
//            throw new ValidacionDeIntegridad("El autor no se encuentra en la base de datos");
//        }
        var autor = autorRepository.findReferenceById(idAutor);
        autor.setPassword(nuevaPassword);

        autorRepository.save(autor);

        return new AutorDTOActualizado(autor);
    }


}
