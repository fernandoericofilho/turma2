package com.example.biblioteca_api.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, length = 150)
    private String autor;

    // Lado inverso do relacionamento N:N (via LivroEmprestimo)
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LivroEmprestimo> livroEmprestimos = new ArrayList<>();

    // ---- Construtores ----
    public Livro() {}

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor  = autor;
    }

    // ---- Getters e Setters ----
    public Long getId()                                  { return id; }
    public void setId(Long id)                           { this.id = id; }

    public String getTitulo()                            { return titulo; }
    public void setTitulo(String titulo)                 { this.titulo = titulo; }

    public String getAutor()                             { return autor; }
    public void setAutor(String autor)                   { this.autor = autor; }

    public List<LivroEmprestimo> getLivroEmprestimos()   { return livroEmprestimos; }
    public void setLivroEmprestimos(List<LivroEmprestimo> le) { this.livroEmprestimos = le; }

    @Override
    public String toString() {
        return "Livro{id=" + id + ", titulo='" + titulo + "', autor='" + autor + "'}";
    }
}