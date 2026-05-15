package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("""
        SELECT l
        FROM Livro l
        JOIN LivroEmprestimo le ON le.livro.id = l.id
        JOIN Emprestimo e ON le.emprestimo.id = e.id
        WHERE e.leitor.id = :leitorId
        """)
    List<Livro> buscarLivrosPorLeitor(Long leitorId);

    @Query(value = """
        SELECT l.titulo, COUNT(le.id)
        FROM livro l
        JOIN livro_emprestimo le ON l.id = le.livro_id
        GROUP BY l.titulo
        """, nativeQuery = true)
    List<Object[]> contarEmprestimosPorLivro();
}