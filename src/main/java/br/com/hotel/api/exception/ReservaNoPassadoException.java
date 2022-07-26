package br.com.hotel.api.exception;

public class ReservaNoPassadoException extends RuntimeException {
    public ReservaNoPassadoException(String message) {
        super(message);
    }
}
