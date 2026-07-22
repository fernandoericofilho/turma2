package com.turma2.biblioteca_api.controllers.response;

import java.time.LocalDate;
import java.util.List;

public record EmprestimoResponse(
        Long id,
        String nomeLeitor,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao,
        List<String> livros
) {
}