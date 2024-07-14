package com.aluracursos.forohub.domain.autor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findReferenceById(Long id);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM Autor a WHERE a.usuario.username = :username")
    boolean existsByUsername(@Param("username") String username);

    @Query("SELECT a FROM Autor a WHERE a.usuario.username = :username")
    UserDetails findByUsername(@Param("username") String username);


    //Autor findByUsuario_Username(String nombreUsuario);

    boolean existsByEmail(String email);
}
