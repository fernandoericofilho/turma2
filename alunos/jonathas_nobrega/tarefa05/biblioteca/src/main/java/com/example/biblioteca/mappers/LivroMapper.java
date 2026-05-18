package com.example.biblioteca.mappers;

import com.example.biblioteca.controller.request.LivroRequest;
import com.example.biblioteca.controller.response.LivroResponse;
import com.example.biblioteca.dtos.LivroDTO;
import com.example.biblioteca.entity.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public LivroDTO toDTO(LivroRequest request) {

        LivroDTO dto = new LivroDTO();

        dto.setTitulo(request.getTitulo());
        dto.setAutor(request.getAutor());

        return dto;
    }

    public Livro toEntity(LivroDTO dto) {

        Livro livro = new Livro();

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());

        return livro;
    }

    public LivroDTO toDTO(Livro livro) {

        LivroDTO dto = new LivroDTO();

        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());

        return dto;
    }

    public LivroResponse toResponse(LivroDTO dto) {

        LivroResponse response = new LivroResponse();

        response.setId(dto.getId());
        response.setTitulo(dto.getTitulo());
        response.setAutor(dto.getAutor());

        return response;
    }
}