package com.example.biblioteca_api.repository;

import com.example.biblioteca_api.model.LivroEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroEmprestimoRepository extends JpaRepository<LivroEmprestimo, Long>{
}