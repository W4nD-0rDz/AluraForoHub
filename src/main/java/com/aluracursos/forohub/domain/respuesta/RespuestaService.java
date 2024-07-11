package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.autor.AutorRepository;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.exceptions.RespuestaNoEncontradaException;
import com.aluracursos.forohub.exceptions.TopicoNoEncontradoException;
import com.aluracursos.forohub.exceptions.UsuarioNoAutorizadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RespuestaService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;


    public RespuestaDTOEditada editarRespuesta(Long topicoId, DatosEdicionRespuesta datos) {
        Optional<Topico> optionalTopico = topicoRepository.findById(topicoId);
        if (!optionalTopico.isPresent()) {
            throw new TopicoNoEncontradoException("Tópico no encontrado");
        }
        Optional<Respuesta> optionalRespuesta = respuestaRepository.findById(datos.respuestaId());
        if (!optionalRespuesta.isPresent()) {
            throw new RespuestaNoEncontradaException("Respuesta no encontrada");
        }
        Respuesta respuesta = optionalRespuesta.get();
        if (!respuesta.getAutor().getId().equals(datos.autorId())) {
            throw new UsuarioNoAutorizadoException("El autor no tiene permiso para editar esta respuesta");
        }
        if(datos.nuevoTtuloDeRespuesta() != null){
            respuesta.setTitulo(datos.nuevoTtuloDeRespuesta());
        }
        if(datos.nuevoContenidoDeRespuesta() != null){
            respuesta.setContenido(datos.nuevoContenidoDeRespuesta());
        }
        respuestaRepository.save(respuesta);
        return new RespuestaDTOEditada(respuesta);
    }

    public void borrarRespuesta(Long topicoId, DatosEliminacionRespuesta datos) {
        Optional<Topico> optionalTopico = topicoRepository.findById(topicoId);
        if (!optionalTopico.isPresent()) {
            throw new TopicoNoEncontradoException("Tópico no encontrado");
        }
        Optional<Respuesta> optionalRespuesta = respuestaRepository.findById(datos.respuestaId());
        if (!optionalRespuesta.isPresent()) {
            throw new RespuestaNoEncontradaException("Respuesta no encontrada");
        }
        Respuesta respuesta = optionalRespuesta.get();
        if (!respuesta.getAutor().getId().equals(datos.autorId())) {
            throw new UsuarioNoAutorizadoException("El autor no tiene permiso para borrar esta respuesta");
        }
        respuestaRepository.delete(respuesta);
    }
}