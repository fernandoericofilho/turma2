package com.dev.sistema_biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "leitor")
public class Leitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 255)
    private String nome;

    @Email
    @Size(max = 255)
    private String email;

    @OneToMany(mappedBy = "leitor")
    private List<Emprestimo> emprestimos;
}
