package com.aluracursos.forohub.domain.autor;

public record DatosAutenticacionAutor(
        String username,
        String password
) {
    public DatosAutenticacionAutor (Autor autor){
        this(autor.getUsername(), autor.getPassword());
    }
}
