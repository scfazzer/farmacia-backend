package com.farmaciasalud.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String mensaje) { super(mensaje); }
}
