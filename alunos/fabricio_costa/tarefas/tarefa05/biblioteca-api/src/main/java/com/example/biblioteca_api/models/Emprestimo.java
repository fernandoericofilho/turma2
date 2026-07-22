package com.example.biblioteca_api.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    // Relacionamento N:1 — muitos empréstimos pertencem a um leitor
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "leitor_id", nullable = false)
    private Leitor leitor;

    // Relacionamento 1:N com a tabela intermediária
    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LivroEmprestimo> livroEmprestimos = new ArrayList<>();

    // ---- Construtores ----
    public Emprestimo() {}

    public Emprestimo(LocalDate dataEmprestimo, Leitor leitor) {
        this.dataEmprestimo = dataEmprestimo;
        this.leitor         = leitor;
    }

    // ---- Getters e Setters ----
    public Long getId()                                  { return id; }
    public void setId(Long id)                           { this.id = id; }

    public LocalDate getDataEmprestimo()                 { return dataEmprestimo; }
    public void setDataEmprestimo(LocalDate d)           { this.dataEmprestimo = d; }

    public LocalDate getDataDevolucao()                  { return dataDevolucao; }
    public void setDataDevolucao(LocalDate d)            { this.dataDevolucao = d; }

    public Leitor getLeitor()                            { return leitor; }
    public void setLeitor(Leitor leitor)                 { this.leitor = leitor; }

    public List<LivroEmprestimo> getLivroEmprestimos()   { return livroEmprestimos; }
    public void setLivroEmprestimos(List<LivroEmprestimo> le) { this.livroEmprestimos = le; }

    /** Verifica se o empréstimo ainda está em aberto */
    public boolean isAtivo() {
        return dataDevolucao == null;
    }

    @Override
    public String toString() {
        return "Emprestimo{id=" + id
                + ", dataEmprestimo=" + dataEmprestimo
                + ", dataDevolucao=" + dataDevolucao
                + ", leitor=" + (leitor != null ? leitor.getNome() : "null") + "}";
    }
}