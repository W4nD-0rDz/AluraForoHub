package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.domain.autor.AutorRepository;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AutorRepository autorRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            var authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.replace("Bearer ", "");
                var nombreUsuario = tokenService.getSubject(authHeader);
                if (nombreUsuario != null) {
                    //forzar el login del usuario cada vez que se haga un request
                    var autor = autorRepository.findByUsername(nombreUsuario);
                    System.out.println(autor.getUsername());
                    if (autor != null) {
                        var authentication = new UsernamePasswordAuthenticationToken(autor.getUsername(),
                                null, autor.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } else {
                    throw new RuntimeException("El username provisto es nulo.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }



}
