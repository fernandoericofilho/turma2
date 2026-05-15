package com.turma2.biblioteca_api.repositories.projections;

import java.time.LocalDate;

public interface LivroEmprestimoProjection {
    String getTitulo();
    String getNome();
    LocalDate getDataEmprestimo();
    LocalDate getDataDevolucao();
}