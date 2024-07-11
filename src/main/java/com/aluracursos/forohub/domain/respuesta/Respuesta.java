package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.autor.Autor;
import com.aluracursos.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Respuesta")
@Table(name = "respuestas")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private Autor autor;

    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaDeCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id", referencedColumnName = "id")
    private Topico topico;

    @NotBlank
    @Column(name = "titulo")
    private String titulo;

    @NotBlank
    @Column(name = "contenido")
    private String contenido;

    public Respuesta(Autor autor, Topico topico, String tituloRespuesta, String contenidoRespuesta){
        this.autor = autor;
        this.fechaDeCreacion = LocalDateTime.now();
        this.topico = topico;
        this.titulo = tituloRespuesta;
        this.contenido = contenidoRespuesta;
    }

}
