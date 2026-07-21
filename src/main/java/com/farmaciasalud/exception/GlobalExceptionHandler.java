package com.farmaciasalud.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            String campo = ((FieldError) err).getField();
            errores.put(campo, err.getDefaultMessage());
        });
        return buildError(HttpStatus.BAD_REQUEST, "Error de validación en los datos enviados", errores);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> manejarNotFound(ResourceNotFoundException ex) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> manejarBusiness(BusinessException ex) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarGeneral(Exception ex) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno: " + ex.getMessage(), null);
    }

    private ResponseEntity<Map<String, Object>> buildError(HttpStatus status, String mensaje, Object detalle) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("mensaje", mensaje);
        if (detalle != null) body.put("detalle", detalle);
        return new ResponseEntity<>(body, status);
    }
}
