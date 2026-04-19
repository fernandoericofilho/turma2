package com.dev.sistema_biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_emprestimo")
    private LocalDateTime dataEmprestimo;

    @Column(name = "data_devolucao")
    private LocalDateTime dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "leitor_id")
    private Leitor leitor;

    @OneToMany(mappedBy = "emprestimo")
    private List<LivroEmprestimo> livroEmprestimos;
}
