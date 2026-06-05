package com.example.biblioteca_api.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Tratamento global de exceções.
 *
 * @RestControllerAdvice intercepta todas as exceções lançadas nos controllers
 * e devolve respostas padronizadas em JSON.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ----------------------------------------------------------------
    // 400 BAD REQUEST — Erros de Validation (@Valid)
    // ----------------------------------------------------------------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult()
          .getAllErrors()
          .forEach(error -> {
              String campo    = ((FieldError) error).getField();
              String mensagem = error.getDefaultMessage();
              erros.put(campo, mensagem);
          });

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status",    400);
        body.put("erro",      "Dados inválidos");
        body.put("campos",    erros);

        return ResponseEntity.badRequest().body(body);         // 400
    }

    // ----------------------------------------------------------------
    // 404 NOT FOUND — Entidade não encontrada
    // ----------------------------------------------------------------
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            EntityNotFoundException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status",    404);
        body.put("erro",      "Recurso não encontrado");
        body.put("mensagem",  ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body); // 404
    }

    // ----------------------------------------------------------------
    // 400 BAD REQUEST — Argumento inválido (ex: e-mail duplicado)
    // ----------------------------------------------------------------
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(
            IllegalArgumentException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status",    400);
        body.put("erro",      "Requisição inválida");
        body.put("mensagem",  ex.getMessage());

        return ResponseEntity.badRequest().body(body);         // 400
    }

    // ----------------------------------------------------------------
    // 500 INTERNAL SERVER ERROR — Erros inesperados
    // ----------------------------------------------------------------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status",    500);
        body.put("erro",      "Erro interno no servidor");
        body.put("mensagem",  ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body); // 500
    }
}