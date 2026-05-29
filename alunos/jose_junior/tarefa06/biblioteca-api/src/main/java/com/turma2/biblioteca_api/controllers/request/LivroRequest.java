package com.turma2.biblioteca_api.controllers.request;

import com.turma2.biblioteca_api.models.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record LivroRequest(
        @NotBlank(message = "Título obrigatório!")
        @Size(min = 3, max = 255, message = "O título deve conter entre 3 e 255 caracteres")
        String titulo,

        @NotBlank(message = "Nome do autor é obrigatório!")
        @Size(min = 3, max = 255, message = "O nome deve conter entre 3 e 255 caracteres")
        String autor,

        @PositiveOrZero
        int estoque) {
}