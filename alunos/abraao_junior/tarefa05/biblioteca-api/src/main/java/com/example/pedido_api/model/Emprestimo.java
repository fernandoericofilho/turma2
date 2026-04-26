package com.example.pedido_api.model;

import com.example.pedido_api.model.Leitor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Emprestimo {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "leitor_id")
    private Leitor leitor;

    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL)
    private List<LivroEmprestimo> livros;
}