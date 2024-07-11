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
        this.username = extraerUsernameDesdeEmail(datos.email());
        this.tipoDeUsuario = datos.tipoDeUsuario();
    }

    public static String extraerUsernameDesdeEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        return email.split("@")[0];
    }

    public void cambiarTipoDeUsuario(TipoDeUsuario tipoDeUsuario){
        this.tipoDeUsuario = tipoDeUsuario;
    }
}
