package com.example.biblioteca_api.mappers;

import com.example.biblioteca_api.controllers.request.LivroRequest;
import com.example.biblioteca_api.controllers.response.LivroResponse;
import com.example.biblioteca_api.dtos.LivroDTO;
import com.example.biblioteca_api.models.Livro;
import org.springframework.stereotype.Component;

/**
 * Mapper responsável por todas as conversões da entidade Livro entre camadas.
 *
 * Fluxo de entrada:  Request → DTO → Entity
 * Fluxo de saída:    Entity  → DTO → Response
 */
@Component
public class LivroMapper {

    // ----------------------------------------------------------------
    // Request → DTO  (HTTP entra, vira DTO para o Service)
    // ----------------------------------------------------------------
    public LivroDTO toDTO(LivroRequest request) {
        if (request == null) return null;
        LivroDTO dto = new LivroDTO();
        dto.setTitulo(request.getTitulo());
        dto.setAutor(request.getAutor());
        return dto;
    }

    // ----------------------------------------------------------------
    // DTO → Entity  (Service manda para o Repository)
    // ----------------------------------------------------------------
    public Livro toEntity(LivroDTO dto) {
        if (dto == null) return null;
        Livro livro = new Livro();
        livro.setId(dto.getId());
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        return livro;
    }

    // ----------------------------------------------------------------
    // Entity → DTO  (Repository devolve, Service converte para DTO)
    // ----------------------------------------------------------------
    public LivroDTO toDTO(Livro livro) {
        if (livro == null) return null;
        return new LivroDTO(livro.getId(), livro.getTitulo(), livro.getAutor());
    }

    // ----------------------------------------------------------------
    // DTO → Response  (Controller converte para HTTP response)
    // ----------------------------------------------------------------
    public LivroResponse toResponse(LivroDTO dto) {
        if (dto == null) return null;
        return new LivroResponse(dto.getId(), dto.getTitulo(), dto.getAutor());
    }
}