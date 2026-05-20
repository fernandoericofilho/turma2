package com.dev.sistema_biblioteca.mapper;

import com.dev.sistema_biblioteca.controller.request.LivroRequest;
import com.dev.sistema_biblioteca.controller.response.LivroResponse;
import com.dev.sistema_biblioteca.dto.LivroDTO;
import com.dev.sistema_biblioteca.entity.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public LivroDTO requestToDto(LivroRequest request) {

        LivroDTO dto = new LivroDTO();

        dto.setTitulo(request.getTitulo());
        dto.setAutor(request.getAutor());
        dto.setEstoque(request.getEstoque());

        return dto;
    }

    public Livro dtoToEntity(LivroDTO dto) {

        Livro livro = new Livro();

        livro.setId(dto.getId());
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setEstoque(dto.getEstoque());

        return livro;
    }

    public LivroDTO entityToDto(Livro livro) {

        LivroDTO dto = new LivroDTO();

        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());
        dto.setEstoque(livro.getEstoque());

        return dto;
    }

    public LivroResponse dtoToResponse(LivroDTO dto) {

        LivroResponse response = new LivroResponse();

        response.setId(dto.getId());
        response.setTitulo(dto.getTitulo());
        response.setAutor(dto.getAutor());
        response.setEstoque(dto.getEstoque());

        return response;
    }
}