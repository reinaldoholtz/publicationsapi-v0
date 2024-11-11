package io.github.publications.publicationsapi.application.jwt;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message){
        super(message);
    }
}
