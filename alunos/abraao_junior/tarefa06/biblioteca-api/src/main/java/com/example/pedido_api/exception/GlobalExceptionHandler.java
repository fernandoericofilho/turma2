package com.example.pedido_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(
            ResourceNotFoundException ex
    ) {

        return ex.getMessage();
    }

    @ExceptionHandler(
            BusinessException.class
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBusiness(
            BusinessException ex
    ) {

        return ex.getMessage();
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidation(
            MethodArgumentNotValidException ex
    ) {

        Map<String, String> erros =
                new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(erro ->
                        erros.put(
                                erro.getField(),
                                erro.getDefaultMessage()
                        )
                );

        return erros;
    }
}