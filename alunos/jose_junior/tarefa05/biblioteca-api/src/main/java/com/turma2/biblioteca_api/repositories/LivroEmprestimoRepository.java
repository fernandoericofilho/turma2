package com.turma2.biblioteca_api.repositories;

import com.turma2.biblioteca_api.models.Livro;
import com.turma2.biblioteca_api.models.LivroEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroEmprestimoRepository extends JpaRepository<LivroEmprestimo, Long> {

    @Query(value = "SELECT titulo, nome, data_emprestimo, data_devolucao FROM livros liv JOIN\n" +
            "livros_emprestimos lemp ON liv.id = lemp.livro_id JOIN\n" +
            "emprestimos emp ON emp.id = lemp.emprestimo_id JOIN\n" +
            "leitores lei ON lei.id = emp.leitor_id\n" +
            "WHERE lei.id = :leitor_id", nativeQuery = true)
    List<Livro> buscarEmprestimosPorLeitor(Long leitorId);

    @Query(value = "SELECT titulo, nome, data_emprestimo, data_devolucao FROM livros liv JOIN\n" +
            "livros_emprestimos lemp ON liv.id = lemp.livro_id JOIN\n" +
            "emprestimos emp ON emp.id = lemp.emprestimo_id JOIN\n" +
            "leitores lei ON lei.id = emp.leitor_id\n" +
            "WHERE liv.id = :livro_id", nativeQuery = true)
    List<Livro> buscarEmprestimosPorLivro(Long livroId);

    @Query(value = "SELECT COUNT(livro_id) AS vezes_emprestado, titulo FROM livros liv JOIN\n" +
            "livros_emprestimos lemp ON liv.id = lemp.livro_id\n" +
            "GROUP BY liv.id;", nativeQuery = true)
    List<Livro> buscarQuantidadeEmprestimosPorLivro();
}