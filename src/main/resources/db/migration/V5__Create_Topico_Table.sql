-- V4__create_topicos_table.sql

CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    fechaDeCreacion TIMESTAMP NOT NULL,
    status VARCHAR(100),
    autor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    contenidoDelTopico TEXT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES autores(id),
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);

