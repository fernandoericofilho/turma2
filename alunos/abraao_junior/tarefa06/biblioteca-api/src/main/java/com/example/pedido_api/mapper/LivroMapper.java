package com.example.pedido_api.mapper;

import com.example.pedido_api.controller.request.LivroRequest;
import com.example.pedido_api.controller.response.LivroResponse;
import com.example.pedido_api.dto.LivroDTO;
import com.example.pedido_api.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public LivroDTO toDTO(LivroRequest request) {

        LivroDTO dto = new LivroDTO();

        dto.setTitulo(request.getTitulo());
        dto.setAutor(request.getAutor());
        dto.setEstoque(request.getEstoque());

        return dto;
    }

    public Livro toEntity(LivroDTO dto) {

        Livro livro = new Livro();

        livro.setId(dto.getId());
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setEstoque(dto.getEstoque());

        return livro;
    }

    public LivroDTO toDTO(Livro livro) {

        LivroDTO dto = new LivroDTO();

        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());
        dto.setEstoque(livro.getEstoque());

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
