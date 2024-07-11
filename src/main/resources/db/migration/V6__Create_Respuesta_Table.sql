-- Crear tabla respuestas

CREATE TABLE respuestas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    autor_id BIGINT NOT NULL,
    fechaDeCreacion DATETIME NOT NULL,
    topico_id BIGINT NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    contenido TEXT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES autores(id),
    FOREIGN KEY (topico_id) REFERENCES topicos(id)
);

