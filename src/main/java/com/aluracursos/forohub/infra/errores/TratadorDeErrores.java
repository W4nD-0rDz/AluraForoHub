package com.aluracursos.forohub.infra.errores;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Error de validación: " + ex.getMessage());
    }

    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExistsException(EntityExistsException ex) {
        return buildResponse(HttpStatus.CONFLICT, "Ya existe un recurso con esa información.");
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleInternalServerError(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor: " + ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<DatosErrorValidacion> errores = ex.getFieldErrors().stream()
                .map(DatosErrorValidacion::new)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        String mensaje = ex.getMessage().contains("not one of the values accepted for Enum class") ?
                "Elija una categoría válida" : ex.getMessage();
        return buildResponse(HttpStatus.BAD_REQUEST, mensaje);
    }

    @ExceptionHandler(ValidacionDeIntegridad.class)
    protected ResponseEntity<Object> handleValidacionDeIntegridad(ValidacionDeIntegridad ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String mensaje) {
        return ResponseEntity.status(status).body(mensaje);
    }

    private record DatosErrorValidacion (String campo, String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
