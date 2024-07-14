package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.domain.autor.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var autor = autorRepository.findByUsername(username);
        if(autor == null){
            throw new UsernameNotFoundException("El usuario (" + username + ") no se encuentra resgistrado");
        }
        return autor;
    }
}
