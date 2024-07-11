-- Insertar datos en la tabla usuarios
INSERT INTO usuarios (username, tipoUsuario) VALUES
('estudiante1', 'ESTUDIANTE'),
('estudiante2', 'ESTUDIANTE'),
('moderador', 'MODERADOR'),
('instructor', 'INSTRUCTOR'),
('admin', 'ADMINISTRADOR');

-- Insertar datos en la tabla autores
INSERT INTO autores (usuario_id, nombreFantasia, email, password) VALUES
((SELECT id FROM usuarios WHERE username = 'estudiante1'), 'Fantasia1', 'estudiante1@example.com', 'clave1234@'),
((SELECT id FROM usuarios WHERE username = 'estudiante2'), 'Fantasia2', 'estudiante2@example.com', 'clave1234@'),
((SELECT id FROM usuarios WHERE username = 'moderador'), 'FantasiaMod', 'moderador@example.com', 'clave1234@'),
((SELECT id FROM usuarios WHERE username = 'instructor'), 'FantasiaInstr', 'instructor@example.com', 'clave1234@'),
((SELECT id FROM usuarios WHERE username = 'admin'), 'FantasiaAdmin', 'admin@example.com', 'clave1234@');
