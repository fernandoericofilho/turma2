package com.example.biblioteca_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emprestimo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataEmprestimo;

    private String dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "leitor_id")
    private Leitor leitor;

    @OneToMany(mappedBy = "emprestimo")
    private List<LivroEmprestimo> itens;
}