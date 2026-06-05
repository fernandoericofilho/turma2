package com.turma2.biblioteca_api.mappers;

import com.turma2.biblioteca_api.controllers.request.LivroRequest;
import com.turma2.biblioteca_api.controllers.response.LivroResponse;
import com.turma2.biblioteca_api.models.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public LivroResponse entityToResponse(Livro livro) {
        return new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getEstoque());
    }

    public Livro requestToEntity(LivroRequest livroRequest) {
        Livro livro = new Livro();
        livro.setAutor(livroRequest.autor());
        livro.setTitulo(livroRequest.titulo());
        livro.setEstoque(livroRequest.estoque());
        return livro;
    }
}