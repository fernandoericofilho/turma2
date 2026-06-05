package com.example.biblioteca_api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "livro_emprestimo")
public class LivroEmprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantidade = 1;

    // Relacionamento N:1 com Livro
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    // Relacionamento N:1 com Emprestimo
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "emprestimo_id", nullable = false)
    private Emprestimo emprestimo;

    // ---- Construtores ----
    public LivroEmprestimo() {}

    public LivroEmprestimo(Livro livro, Emprestimo emprestimo, int quantidade) {
        this.livro       = livro;
        this.emprestimo  = emprestimo;
        this.quantidade  = quantidade;
    }

    // ---- Getters e Setters ----
    public Long getId()                        { return id; }
    public void setId(Long id)                 { this.id = id; }

    public int getQuantidade()                 { return quantidade; }
    public void setQuantidade(int quantidade)  { this.quantidade = quantidade; }

    public Livro getLivro()                    { return livro; }
    public void setLivro(Livro livro)          { this.livro = livro; }

    public Emprestimo getEmprestimo()          { return emprestimo; }
    public void setEmprestimo(Emprestimo e)    { this.emprestimo = e; }

    @Override
    public String toString() {
        return "LivroEmprestimo{id=" + id
                + ", livro=" + (livro != null ? livro.getTitulo() : "null")
                + ", quantidade=" + quantidade + "}";
    }
}