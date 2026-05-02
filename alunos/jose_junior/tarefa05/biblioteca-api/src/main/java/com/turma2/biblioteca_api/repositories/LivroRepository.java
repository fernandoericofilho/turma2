package com.turma2.biblioteca_api.repositories;

import com.turma2.biblioteca_api.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByAutor(String autor);
}