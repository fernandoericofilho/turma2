package com.example.biblioteca.repository;

import com.example.biblioteca.entity.LivroEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroEmprestimoRepository extends JpaRepository<LivroEmprestimo, Long> {
}