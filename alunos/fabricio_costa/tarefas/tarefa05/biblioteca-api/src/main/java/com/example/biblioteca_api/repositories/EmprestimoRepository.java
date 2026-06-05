package com.example.biblioteca_api.repositories;

import com.example.biblioteca_api.models.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // Todos os empréstimos de um leitor
    List<Emprestimo> findByLeitorId(Long leitorId);

    // Empréstimos ativos (sem data de devolução)
    List<Emprestimo> findByDataDevolucaoIsNull();

    // Empréstimos de um leitor que ainda estão ativos
    List<Emprestimo> findByLeitorIdAndDataDevolucaoIsNull(Long leitorId);

    /**
     * JPQL: lista todos os empréstimos com seus livros carregados
     * (evita N+1 queries).
     */
    @Query("""
           SELECT DISTINCT e FROM Emprestimo e
           JOIN FETCH e.leitor
           JOIN FETCH e.livroEmprestimos le
           JOIN FETCH le.livro
           """)
    List<Emprestimo> findAllComLivros();

    /**
     * Consulta SQL equivalente ao requisito "Listar todos os livros emprestados"
     * (para usar no console H2 também):
     *
     *   SELECT l.titulo, l.autor, e.data_emprestimo, lt.nome AS leitor
     *   FROM livro l
     *   JOIN livro_emprestimo le ON l.id = le.livro_id
     *   JOIN emprestimo e        ON le.emprestimo_id = e.id
     *   JOIN leitor lt           ON e.leitor_id = lt.id
     *   WHERE e.data_devolucao IS NULL;
     */
    @Query(value = """
           SELECT l.titulo, l.autor, e.data_emprestimo, lt.nome AS leitor
           FROM livro l
           JOIN livro_emprestimo le ON l.id = le.livro_id
           JOIN emprestimo e        ON le.emprestimo_id = e.id
           JOIN leitor lt           ON e.leitor_id = lt.id
           WHERE e.data_devolucao IS NULL
           """, nativeQuery = true)
    List<Object[]> listarLivrosEmprestadosAtivos();
}