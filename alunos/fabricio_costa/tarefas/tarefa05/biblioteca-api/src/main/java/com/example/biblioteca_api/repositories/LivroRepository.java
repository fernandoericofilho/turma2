package com.example.biblioteca_api.repositories;

import com.example.biblioteca_api.models.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Paginação nativa do JpaRepository
    Page<Livro> findAll(Pageable pageable);

    // Busca por título (parcial, case-insensitive)
    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    // Busca por autor
    List<Livro> findByAutorIgnoreCase(String autor);
}