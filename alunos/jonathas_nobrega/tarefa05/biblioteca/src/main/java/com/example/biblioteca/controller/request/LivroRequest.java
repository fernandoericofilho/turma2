package com.example.biblioteca.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LivroRequest {

    @NotBlank(message = "Título obrigatório")
    @Size(min = 3, max = 100)
    private String titulo;

    @NotBlank(message = "Autor obrigatório")
    private String autor;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}