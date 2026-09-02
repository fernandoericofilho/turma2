package br.com.jonathas.stashflow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IdempotencyConflictException
        extends RuntimeException {

    public IdempotencyConflictException() {
        super(
                "Idempotency key was already used with a different request"
        );
    }
}