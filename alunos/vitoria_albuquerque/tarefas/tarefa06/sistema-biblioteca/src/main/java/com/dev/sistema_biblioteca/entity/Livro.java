package com.dev.sistema_biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título obrigatório")
    @Column(nullable = false)
    @Size(min = 2, max = 255)
    private String titulo;

    @NotBlank(message = "Autor obrigatório")
    @Column(nullable = false)
    @Size(min = 2, max = 255)
    private String autor;

    @OneToMany(mappedBy = "livro")
    private List<LivroEmprestimo> livroEmprestimos;
}