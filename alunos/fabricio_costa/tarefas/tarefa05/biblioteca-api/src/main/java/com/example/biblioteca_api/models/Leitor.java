package com.example.biblioteca_api.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "leitor")
public class Leitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    // Relacionamento 1:N — um leitor tem vários empréstimos
    @OneToMany(mappedBy = "leitor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emprestimo> emprestimos = new ArrayList<>();

    // ---- Construtores ----
    public Leitor() {}

    public Leitor(String nome, String email) {
        this.nome  = nome;
        this.email = email;
    }

    // ---- Getters e Setters ----
    public Long getId()                          { return id; }
    public void setId(Long id)                   { this.id = id; }

    public String getNome()                      { return nome; }
    public void setNome(String nome)             { this.nome = nome; }

    public String getEmail()                     { return email; }
    public void setEmail(String email)           { this.email = email; }

    public List<Emprestimo> getEmprestimos()     { return emprestimos; }
    public void setEmprestimos(List<Emprestimo> e) { this.emprestimos = e; }

    @Override
    public String toString() {
        return "Leitor{id=" + id + ", nome='" + nome + "', email='" + email + "'}";
    }
}