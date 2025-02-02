package com.aluracursos.forohub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Topico findReferenceById(Long id);

    boolean existsByContenidoDelTopico(String mensaje);
}
