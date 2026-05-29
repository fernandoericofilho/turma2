package com.turma2.biblioteca_api.controllers.request;

import java.time.LocalDate;
import java.util.List;

public record EmprestimoRequest(
        Long leitorId,
        List<Long> livrosIds,
        LocalDate dataDevolucao) {
}