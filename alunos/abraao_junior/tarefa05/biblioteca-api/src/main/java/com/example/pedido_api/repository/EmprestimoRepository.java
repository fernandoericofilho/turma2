package com.example.pedido_api.repository;

import com.example.pedido_api.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    @Query(value = """
        SELECT l.*
        FROM livro l
        JOIN livro_emprestimo le ON l.id = le.livro_id
        JOIN emprestimo e ON le.emprestimo_id = e.id
        WHERE e.leitor_id = :leitorId
    """, nativeQuery = true)
    List<Emprestimo> findByLeitorId(@Param("leitorId") Long leitorId);

    @Query(value = """
        SELECT l.titulo as titulo, COUNT(le.livro_id) AS total_emprestimo
        FROM livro l
        LEFT JOIN livro_emprestimo le ON l.id = le.livro_id
        GROUP BY l.id, l.titulo
    """, nativeQuery = true)
    List<Object[]> contarEmprestimosPorLivro();
}