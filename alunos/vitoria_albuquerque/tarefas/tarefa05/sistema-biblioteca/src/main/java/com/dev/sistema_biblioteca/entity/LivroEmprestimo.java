package com.dev.sistema_biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "livro_emprestimo")
public class LivroEmprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_livro", nullable = false)
    @NotNull
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "id_emprestimo", nullable = false)
    @NotNull
    private Emprestimo emprestimo;

    @Positive
    private Integer quantidade;
}
