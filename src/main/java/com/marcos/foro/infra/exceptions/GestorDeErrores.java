package com.marcos.foro.infra.exceptions;

import com.marcos.foro.models.topicos.DatosRegistroTopico;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GestorDeErrores {

    //PARA LOS METODOS DE REFERENCE BY ID
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarError400() {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorValidacion(ValidationException ex) {
        var error = ex.getMessage();
        return ResponseEntity.badRequest().body(error);
    }

    //PARA CUANDO FALLA UNA VALIDACION EN LOS RECORDS
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionarErrorException(MethodArgumentNotValidException ex) {
        var camposErrores = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(camposErrores.stream().map(DatosErrorValidacion::new));
    }

    //PARA FALLA LOS CAMPOS UNICOS EN SQL
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity errorDatosDuplicados(SQLIntegrityConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity gestionarException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getLocalizedMessage());
    }

    public record DatosErrorValidacion(
            String campo,
            String mensaje
    ) {
        public DatosErrorValidacion(FieldError fieldError) {
            this(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            );
        }

    }


}
