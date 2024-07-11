-- Crear tabla autores
CREATE TABLE autores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    nombreFantasia VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(400),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);