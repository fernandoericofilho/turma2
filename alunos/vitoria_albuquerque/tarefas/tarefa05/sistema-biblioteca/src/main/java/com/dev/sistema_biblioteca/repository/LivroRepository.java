package com.dev.sistema_biblioteca.repository;

import com.dev.sistema_biblioteca.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
