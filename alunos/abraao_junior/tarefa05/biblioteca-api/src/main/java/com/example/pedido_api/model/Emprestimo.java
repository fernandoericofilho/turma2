package com.example.pedido_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Emprestimo {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate dataEmprestimo;

    @NotNull
    private LocalDate dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "leitor_id")
    private Leitor leitor;

    @OneToMany(mappedBy = "emprestimo")
    private List<LivroEmprestimo> livros;
}