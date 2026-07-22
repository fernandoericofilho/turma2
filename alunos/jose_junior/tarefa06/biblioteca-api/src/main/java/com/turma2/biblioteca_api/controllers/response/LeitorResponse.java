package com.turma2.biblioteca_api.controllers.response;

import com.turma2.biblioteca_api.models.Leitor;

public record LeitorResponse(
        Long id,
        String nome,
        String email
) {
}