package com.turma2.biblioteca_api.controllers.request;

import com.turma2.biblioteca_api.models.Leitor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LeitorRequest(
        @NotBlank(message = "Nome do leitor é obrigatório!")
        @Size(min = 3, max = 255, message = "O nome deve conter entre 3 e 255 caracteres")
        String nome,
        @Email
        String email) {
}