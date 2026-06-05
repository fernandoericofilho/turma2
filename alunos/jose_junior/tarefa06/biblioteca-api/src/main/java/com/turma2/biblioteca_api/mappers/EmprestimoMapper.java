package com.turma2.biblioteca_api.mappers;

import com.turma2.biblioteca_api.controllers.response.EmprestimoResponse;
import com.turma2.biblioteca_api.models.Emprestimo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmprestimoMapper {

    public EmprestimoResponse entityToResponse(Emprestimo emprestimo) {

        List<String> livros = emprestimo.getLivros()
                .stream()
                .map(livroEmprestimo ->
                        livroEmprestimo.getLivro().getTitulo())
                .toList();

        return new EmprestimoResponse(
                emprestimo.getId(),
                emprestimo.getLeitor().getNome(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao(),
                livros
        );
    }
}