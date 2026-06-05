package com.turma2.biblioteca_api.controllers.response;

import com.turma2.biblioteca_api.models.Livro;

public record LivroResponse(
        Long id,
        String titulo,
        String autor,
        int estoque
) {
}