package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.autor.Autor;
import com.aluracursos.forohub.domain.autor.AutorRepository;
import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.respuesta.DatosGeneracionDeRespuesta;
import com.aluracursos.forohub.domain.respuesta.Respuesta;
import com.aluracursos.forohub.domain.respuesta.RespuestaRepository;
import com.aluracursos.forohub.domain.respuesta.RespuestaService;
import com.aluracursos.forohub.exceptions.TopicoNoEncontradoException;
import com.aluracursos.forohub.exceptions.UsuarioNoAutorizadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private RespuestaService respuestaService;


    public TopicoDTORegistrado crearTopico(DatosRegistroTopico datos){
//        if(autorRepository.findById(datos.idAutor()).isEmpty()){
//            throw new ValidacionDeIntegridad("El autor no se encuentra en la base de datos");
//        }
//
//        if(cursoRepository.findById(datos.idCurso()).isEmpty()){
//            throw new ValidacionDeIntegridad("El curso de referencia no se encuentra en la base de datos");
//        }

        var autor = autorRepository.findReferenceById(datos.idAutor());
        var curso = cursoRepository.findReferenceById(datos.idCurso());
        var topico = new Topico(autor, curso, datos);
        topicoRepository.save(topico);

        return new TopicoDTORegistrado(topico);
    }

    public void agregarRespuesta(Long topicoId, DatosGeneracionDeRespuesta datos) {
        Optional<Topico> optionalTopico = topicoRepository.findById(topicoId);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            Optional<Autor> optionalAutor = autorRepository.findById(datos.autorId());
            if (optionalAutor.isPresent()) {
                Autor autor = optionalAutor.get();
                String tituloRespuesta = "Re: " + topico.getTitulo();
                Respuesta respuesta = new Respuesta(autor, topico, tituloRespuesta, datos.contenidoRespuesta());
                topico.agregarRespuestas(respuesta);
                topico.topicoRespondido();
                respuestaRepository.save(respuesta);
                topicoRepository.save(topico);
            } else {
                throw new RuntimeException("Autor no encontrado");
            }
        } else {
            throw new RuntimeException("Tópico no encontrado");
        }
    }

    public TopicoDTORegistrado actualizarTopico(Long topicoId, DatosActualizacionTopico datos) {
        Optional<Topico> optionalTopico = topicoRepository.findById(topicoId);
        if (optionalTopico.isEmpty()) {
            throw new RuntimeException("Tópico no encontrado");
        }
        Topico topico = optionalTopico.get();
        Optional<Autor> optionalAutor = autorRepository.findById(datos.autorId());
        if (optionalAutor.isEmpty()) {
            throw new RuntimeException("Autor no encontrado en la base de datos");
        }
        if (!topico.getAutor().getId().equals(datos.autorId())) {
            throw new RuntimeException("El autor no tiene permiso para editar este tópico");
        }
        Curso nuevoCurso;
        if (datos.idCurso() != null) {
            nuevoCurso = cursoRepository.findReferenceById(datos.idCurso());
        } else {
            nuevoCurso = topico.getCurso();
        }
        topico.actualizarTopico(nuevoCurso, datos);
        topicoRepository.save(topico);
        return new TopicoDTORegistrado(topico);
    }

    public void cerrarTopico(Long topicoId, Long autorId){
        Optional<Topico> optionalTopico = topicoRepository.findById(topicoId);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            if (topico.getAutor().getId().equals(autorId) && !topico.getRespuestas().isEmpty()) {
                topico.topicoCerrado();
                topicoRepository.save(topico);
            }
            throw new UsuarioNoAutorizadoException("El autor no tiene permiso para cerrar este tópico o no tiene suficientes respuestas");
        }
        throw new TopicoNoEncontradoException("Tópico no encontrado");
    }

    public void borrarTopico(Long topicoId, Long autorId) {
        Optional<Topico> optionalTopico = topicoRepository.findById(topicoId);
        if (!optionalTopico.isPresent()) {
            throw new TopicoNoEncontradoException("Tópico no encontrado");
        }
        Topico topico = optionalTopico.get();
        if (!topico.getAutor().getId().equals(autorId)) {
            throw new UsuarioNoAutorizadoException("El autor no tiene permiso para borrar este tópico");
        }
        topicoRepository.delete(topico);
    }
}
