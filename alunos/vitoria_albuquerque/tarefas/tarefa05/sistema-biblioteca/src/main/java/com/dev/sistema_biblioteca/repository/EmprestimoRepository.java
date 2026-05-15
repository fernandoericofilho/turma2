package com.dev.sistema_biblioteca.repository;

import com.dev.sistema_biblioteca.entity.Emprestimo;
import com.dev.sistema_biblioteca.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query(value = """
                SELECT l.id, l.titulo, l.autor, lt.nome as nome_leitor
                FROM livro_emprestimo le
                JOIN emprestimo e ON le.id_emprestimo = e.id
                JOIN leitor lt ON e.leitor_id = lt.id
                JOIN livro l ON le.id_livro = l.id
                WHERE lt.id = :leitorId
            """, nativeQuery = true)
    List<Livro> buscarLivrosPorLeitor(@Param("leitorId") Long leitorId);
}
