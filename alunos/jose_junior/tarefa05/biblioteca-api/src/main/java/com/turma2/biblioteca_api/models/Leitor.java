package com.turma2.biblioteca_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "leitores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leitor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    @OneToMany(mappedBy = "leitor", cascade = CascadeType.ALL)
    private List<Emprestimo> emprestimos;
}