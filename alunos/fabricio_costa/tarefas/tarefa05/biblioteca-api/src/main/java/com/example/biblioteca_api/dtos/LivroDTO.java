package com.example.biblioteca_api.dtos;

public class LivroDTO {

    private Long   id;
    private String titulo;
    private String autor;

    public LivroDTO() {}

    public LivroDTO(Long id, String titulo, String autor) {
        this.id     = id;
        this.titulo = titulo;
        this.autor  = autor;
    }

    public Long getId()              { return id; }
    public void setId(Long id)       { this.id = id; }
    public String getTitulo()        { return titulo; }
    public void setTitulo(String t)  { this.titulo = t; }
    public String getAutor()         { return autor; }
    public void setAutor(String a)   { this.autor = a; }
}