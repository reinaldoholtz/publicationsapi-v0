package io.github.publications.publicationsapi.domain.exception;

public class DuplicateTupleException extends RuntimeException{
    public DuplicateTupleException(String message){
        super(message);
    }
}
