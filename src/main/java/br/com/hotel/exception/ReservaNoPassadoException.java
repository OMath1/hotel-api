package br.com.hotel.exception;

public class ReservaNoPassadoException extends RuntimeException {
    public ReservaNoPassadoException(String message) {
        super(message);
    }
}
