package com.dev.sistema_biblioteca.entity;

import jakarta.persistence.*;
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
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "id_emprestimo", nullable = false)
    private Emprestimo emprestimo;

    private Integer quantidade;
}
