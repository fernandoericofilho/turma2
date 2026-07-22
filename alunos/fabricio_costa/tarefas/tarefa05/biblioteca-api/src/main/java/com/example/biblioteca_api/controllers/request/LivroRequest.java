package com.example.biblioteca_api.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LivroRequest {

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 2, max = 200, message = "Título deve ter entre 2 e 200 caracteres")
    private String titulo;

    @NotBlank(message = "Autor é obrigatório")
    @Size(min = 2, max = 150, message = "Autor deve ter entre 2 e 150 caracteres")
    private String autor;

    public LivroRequest() {}

    public LivroRequest(String titulo, String autor) {
        this.titulo = titulo;
        this.autor  = autor;
    }

    public String getTitulo()              { return titulo; }
    public void setTitulo(String titulo)   { this.titulo = titulo; }
    public String getAutor()               { return autor; }
    public void setAutor(String autor)     { this.autor = autor; }
}