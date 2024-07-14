package com.aluracursos.forohub.domain.usuario;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Usuario")
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "tipoUsuario")
    @Enumerated(EnumType.STRING)
    private TipoDeUsuario tipoDeUsuario;


    public Usuario(DatosAltaUsuario datos) {
        this.username = datos.username();
        if (datos.tipoDeUsuario() == null) {
            this.tipoDeUsuario = TipoDeUsuario.ESTUDIANTE; // Establecer un valor por defecto
        } else {
            this.tipoDeUsuario = datos.tipoDeUsuario();
        }
    }

    public void cambiarTipoDeUsuario(TipoDeUsuario tipoDeUsuario){
        this.tipoDeUsuario = tipoDeUsuario;
    }
}

//    public static String extraerUsernameDesdeEmail(String email) {
//        if (email == null || !email.contains("@")) {
//            throw new IllegalArgumentException("Email inválido");
//        }
//        return email.split("@")[0];
//    }
