package com.dev.sistema_biblioteca.repository;
import com.dev.sistema_biblioteca.entity.LivroEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroEmprestimoRepository extends JpaRepository<LivroEmprestimo, Long> {

    @Query(value = """
                SELECT l.id, l.titulo, l.autor, SUM(le.quantidade) as totalEmprestimos
                FROM livro l
                JOIN livro_emprestimo le ON le.id_livro = l.id
                GROUP BY l.id, l.titulo, l.autor
            """, nativeQuery = true)
    List<Object[]> contarEmprestimosPorLivro();
}
