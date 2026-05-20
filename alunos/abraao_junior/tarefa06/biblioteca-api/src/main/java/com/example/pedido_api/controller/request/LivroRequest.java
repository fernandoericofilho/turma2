package com.example.pedido_api.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroRequest {

    @NotBlank(message = "Título obrigatório")
    @Size(min = 3, max = 100)
    private String titulo;

    @NotBlank(message = "Autor obrigatório")
    private String autor;

    @NotNull
    @Min(value = 1, message = "Estoque deve ter no mínimo 1")
    private Integer estoque;
}
