package com.turma2.biblioteca_api.controllers;

import com.turma2.biblioteca_api.controllers.request.LivroRequest;
import com.turma2.biblioteca_api.controllers.response.LivroResponse;
import com.turma2.biblioteca_api.services.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<List<LivroResponse>> listarTodosOsLivros() {
        var livros = livroService.listarTodosOsLivros();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> buscarLivroPorId(@PathVariable Long id) {
        LivroResponse livroResponse = livroService.buscarLivroPorId(id);
        return ResponseEntity.ok(livroResponse);
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<LivroResponse>> buscarLivrosPorTitulo(@RequestParam String titulo) {
        var livros = livroService.buscarLivrosPorTitulo(titulo);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/autor")
    public ResponseEntity<List<LivroResponse>> buscarLivrosPorAutor(@RequestParam String autor) {
        var livros = livroService.buscarLivrosPorAutor(autor);
        return ResponseEntity.ok(livros);
    }

    @PostMapping
    public ResponseEntity<LivroResponse> cadastrar(@RequestBody @Valid LivroRequest livroRequest) {
        LivroResponse livroResponse = livroService.cadastrarLivro(livroRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroResponse);
    }
}