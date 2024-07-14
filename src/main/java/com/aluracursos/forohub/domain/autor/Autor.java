package com.aluracursos.forohub.domain.autor;


import com.aluracursos.forohub.domain.respuesta.Respuesta;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.usuario.DatosAltaUsuario;
import com.aluracursos.forohub.domain.usuario.TipoDeUsuario;
import com.aluracursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Autor")
@Table(name = "autores")
public class Autor implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "nombreFantasia")
    private String nombreFantasia;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topico> listaDeTopicos;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> listaDeRespuestas;

    public Autor(DatosAltaAutor datos) {
        this.email = datos.email();
        this.usuario = new Usuario(new DatosAltaUsuario(datos.username(), datos.tipoDeUsuario()));
        this.nombreFantasia = datos.nombreFantasia();
        this.password = datos.password();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return usuario.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
