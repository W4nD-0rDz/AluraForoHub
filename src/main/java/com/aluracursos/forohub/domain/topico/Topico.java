package com.aluracursos.forohub.domain.topico;


import com.aluracursos.forohub.domain.autor.Autor;
import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.respuesta.Respuesta;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Topico")
@Table(name = "topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "titulo")
    private String titulo;

    @NotNull
    private LocalDateTime fechaDeCreacion;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private Autor autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", referencedColumnName = "id")
    private Curso curso;

    @NotNull
    private String contenidoDelTopico;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas;

    public Topico(Autor autor, Curso curso, DatosRegistroTopico datos){
        this.titulo = datos.titulo();
        this.fechaDeCreacion = LocalDateTime.now();
        this.status = Status.ABIERTO;
        this.autor = autor;
        this.curso = curso;
        this.contenidoDelTopico = datos.mensaje();
    }

    public void agregarRespuestas(Respuesta respuesta){
        this.respuestas.add(respuesta);
    }

    public void topicoRespondido(){
        if(this.respuestas.size()>1){
            this.status = Status.RESPONDIDO;
        }
    }

    public void actualizarTopico(Curso nuevoCurso, DatosActualizacionTopico datos) {
        if(datos.idCurso() != null){
            this.curso = nuevoCurso;
        }
        if(datos.titulo() != null){
            this.titulo = datos.titulo();
        }
        if(datos.mensaje() != null){
            this.contenidoDelTopico = datos.mensaje();
        }
    }

    public void topicoCerrado(){
        this.status = Status.CERRADO;
    }

}
