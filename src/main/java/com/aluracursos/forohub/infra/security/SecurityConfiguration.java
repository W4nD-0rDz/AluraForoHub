package com.aluracursos.forohub.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS))
                .authorizeRequests(auth -> auth
                        // Permitir acceso a todas las rutas sin autenticación
                        //.requestMatchers("/**").permitAll()
                        // Configuraciones para la documentación
                        .requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        // Permisos personalizados basados en TipoDeUsuario
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/topicos").hasAnyRole("ESTUDIANTE", "MODERADOR")
                        .requestMatchers(HttpMethod.POST, "/respuestas").hasAnyRole("INSTRUCTOR", "ESTUDIANTE", "MODERADOR")
                        .requestMatchers(HttpMethod.PUT, "/topicos/{id}", "/respuestas/{id}").hasAnyRole("ESTUDIANTE", "MODERADOR")
                        .requestMatchers(HttpMethod.PUT, "/respuestas/{id}").hasAnyRole("INSTRUCTOR", "ESTUDIANTE", "MODERADOR")
                        .requestMatchers(HttpMethod.DELETE, "/respuestas/{id}").hasAnyRole("INSTRUCTOR", "ESTUDIANTE", "MODERADOR")
                        .requestMatchers(HttpMethod.DELETE, "/topicos").hasAnyRole("ESTUDIANTE", "MODERADOR")
                        .requestMatchers(HttpMethod.GET, "/topicos", "/respuestas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/autores").hasRole("ADMINISTRADOR")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}