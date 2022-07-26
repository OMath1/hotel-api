package br.com.hotel.api.exception;

public class EstadiaInvalidaException extends RuntimeException {
    public EstadiaInvalidaException(String message) {
        super(message);
    }
}
