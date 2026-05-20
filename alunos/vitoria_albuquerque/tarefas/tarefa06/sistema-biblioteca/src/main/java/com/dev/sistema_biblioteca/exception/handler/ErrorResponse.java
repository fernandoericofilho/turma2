package com.dev.sistema_biblioteca.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private List<FieldError> errors;

    public ErrorResponse(String message) {
        this.message = message;
        this.errors = null;
    }

    @Getter
    @AllArgsConstructor
    public static class FieldError {
        private String field;
        private String message;
    }
}