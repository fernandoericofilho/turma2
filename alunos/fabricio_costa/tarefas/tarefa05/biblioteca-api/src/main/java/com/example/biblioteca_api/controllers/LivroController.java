package com.example.biblioteca_api.controllers;

import com.example.biblioteca_api.controllers.request.LivroRequest;
import com.example.biblioteca_api.controllers.response.LivroResponse;
import com.example.biblioteca_api.dtos.LivroDTO;
import com.example.biblioteca_api.mappers.LivroMapper;
import com.example.biblioteca_api.services.LivroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller responsável pelos endpoints REST de Livro.
 *
 * REGRAS:
 *  - Recebe Request, devolve Response (NUNCA Entity ou DTO)
 *  - Chama Service, nunca Repository diretamente
 *  - Sem regras de negócio aqui
 *  - Gerencia apenas status HTTP e mapeamento HTTP ↔ DTO
 */
@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;
    private final LivroMapper  livroMapper;

    public LivroController(LivroService livroService, LivroMapper livroMapper) {
        this.livroService = livroService;
        this.livroMapper  = livroMapper;
    }

    // ----------------------------------------------------------------
    // POST /livros  →  201 CREATED
    // ----------------------------------------------------------------
    @PostMapping
    public ResponseEntity<LivroResponse> cadastrar(
            @RequestBody @Valid LivroRequest request) {

        LivroDTO dto      = livroMapper.toDTO(request);      // Request → DTO
        LivroDTO salvo    = livroService.cadastrar(dto);      // Service
        LivroResponse res = livroMapper.toResponse(salvo);   // DTO → Response

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(res.getId())
                .toUri();

        return ResponseEntity.created(location).body(res);   // 201 CREATED
    }

    // ----------------------------------------------------------------
    // GET /livros?page=0&size=5  →  200 OK (paginado)
    // ----------------------------------------------------------------
    @GetMapping
    public ResponseEntity<Page<LivroResponse>> listarTodos(
            @PageableDefault(size = 5, sort = "titulo") Pageable pageable) {

        Page<LivroResponse> page = livroService
                .listarTodos(pageable)
                .map(livroMapper::toResponse);               // DTO → Response

        return ResponseEntity.ok(page);                      // 200 OK
    }

    // ----------------------------------------------------------------
    // GET /livros/{id}  →  200 OK  ou  404 NOT FOUND
    // ----------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> buscarPorId(@PathVariable Long id) {
        LivroDTO dto = livroService.buscarPorId(id);
        return ResponseEntity.ok(livroMapper.toResponse(dto)); // 200 OK
        // 404 é lançado pelo GlobalExceptionHandler via EntityNotFoundException
    }

    // ----------------------------------------------------------------
    // GET /livros/titulo?titulo=Clean Code  →  200 OK
    // ----------------------------------------------------------------
    @GetMapping("/titulo")
    public ResponseEntity<List<LivroResponse>> buscarPorTitulo(
            @RequestParam String titulo) {

        List<LivroResponse> lista = livroService
                .buscarPorTitulo(titulo)
                .stream()
                .map(livroMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);                     // 200 OK
    }

    // ----------------------------------------------------------------
    // PUT /livros/{id}  →  200 OK  ou  404 NOT FOUND
    // ----------------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid LivroRequest request) {

        LivroDTO dto       = livroMapper.toDTO(request);
        LivroDTO atualizado = livroService.atualizar(id, dto);
        return ResponseEntity.ok(livroMapper.toResponse(atualizado)); // 200 OK
    }

    // ----------------------------------------------------------------
    // DELETE /livros/{id}  →  204 NO CONTENT
    // ----------------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();           // 204 NO CONTENT
    }
}