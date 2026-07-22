package com.turma2.biblioteca_api.repositories;

import com.turma2.biblioteca_api.repositories.projections.LivroEmprestimoProjection;
import com.turma2.biblioteca_api.repositories.projections.QuantidadeEmprestimosLivroProjection;
import com.turma2.biblioteca_api.models.LivroEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroEmprestimoRepository extends JpaRepository<LivroEmprestimo, Long> {

    @Query(value = "SELECT titulo, nome, data_emprestimo AS dataEmprestimo, data_devolucao AS dataDevolucao FROM livros liv JOIN\n" +
            "livros_emprestimos lemp ON liv.id = lemp.livro_id JOIN\n" +
            "emprestimos emp ON emp.id = lemp.emprestimo_id JOIN\n" +
            "leitores lei ON lei.id = emp.leitor_id\n" +
            "WHERE lei.id = :leitorId", nativeQuery = true)
    List<LivroEmprestimoProjection> buscarEmprestimosPorLeitor(Long leitorId);

    @Query(value = "SELECT titulo, nome, data_emprestimo AS dataEmprestimo, data_devolucao AS dataDevolucao FROM livros liv JOIN\n" +
            "livros_emprestimos lemp ON liv.id = lemp.livro_id JOIN\n" +
            "emprestimos emp ON emp.id = lemp.emprestimo_id JOIN\n" +
            "leitores lei ON lei.id = emp.leitor_id\n" +
            "WHERE liv.id = :livroId", nativeQuery = true)
    List<LivroEmprestimoProjection> buscarEmprestimosPorLivro(Long livroId);

    @Query(value = "SELECT COUNT(livro_id) AS vezesEmprestado, titulo FROM livros liv JOIN\n" +
            "livros_emprestimos lemp ON liv.id = lemp.livro_id\n" +
            "GROUP BY liv.id, liv.titulo;", nativeQuery = true)
    List<QuantidadeEmprestimosLivroProjection> buscarQuantidadeEmprestimosPorLivro();
}