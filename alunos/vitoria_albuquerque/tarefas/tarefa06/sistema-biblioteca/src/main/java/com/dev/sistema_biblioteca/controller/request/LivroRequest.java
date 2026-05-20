package com.dev.sistema_biblioteca.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LivroRequest {

    @NotBlank(message = "Título obrigatório")
    @Size(min = 2, max = 255)
    private String titulo;

    @NotBlank(message = "Autor obrigatório")
    @Size(min = 2, max = 255)
    private String autor;

    @NotNull
    @PositiveOrZero
    private int estoque;
}