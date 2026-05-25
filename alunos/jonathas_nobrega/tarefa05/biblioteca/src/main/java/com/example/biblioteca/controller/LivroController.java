package com.example.biblioteca.controller;

import com.example.biblioteca.controller.request.LivroRequest;
import com.example.biblioteca.controller.response.LivroResponse;
import com.example.biblioteca.dtos.LivroDTO;
import com.example.biblioteca.mappers.LivroMapper;
import com.example.biblioteca.services.LivroService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    public LivroController(
            LivroService livroService,
            LivroMapper livroMapper
    ) {
        this.livroService = livroService;
        this.livroMapper = livroMapper;
    }

    @PostMapping
    public ResponseEntity<LivroResponse> cadastrar(
            @RequestBody @Valid LivroRequest request
    ) {

        LivroDTO dto = livroMapper.toDTO(request);

        dto = livroService.cadastrar(dto);

        LivroResponse response =
                livroMapper.toResponse(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Page<LivroResponse>> listar(
            Pageable pageable
    ) {

        Page<LivroResponse> response =
                livroService
                        .listar(pageable)
                        .map(livroMapper::toResponse);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> buscarPorId(
            @PathVariable Long id
    ) {

        LivroDTO dto = livroService.buscarPorId(id);

        return ResponseEntity.ok(
                livroMapper.toResponse(dto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid LivroRequest request
    ) {

        LivroDTO dto = livroMapper.toDTO(request);

        dto = livroService.atualizar(id, dto);

        return ResponseEntity.ok(
                livroMapper.toResponse(dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        livroService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}