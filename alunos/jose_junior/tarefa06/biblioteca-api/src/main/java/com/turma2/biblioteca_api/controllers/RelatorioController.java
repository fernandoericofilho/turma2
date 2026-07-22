package com.turma2.biblioteca_api.controllers;

import com.turma2.biblioteca_api.repositories.projections.LivroEmprestimoProjection;
import com.turma2.biblioteca_api.repositories.projections.QuantidadeEmprestimosLivroProjection;
import com.turma2.biblioteca_api.services.LivroEmprestimoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final LivroEmprestimoService livroEmprestimoService;

    public RelatorioController(LivroEmprestimoService livroEmprestimoService) {
        this.livroEmprestimoService = livroEmprestimoService;
    }

    @GetMapping("/emprestimos/leitor/{leitorId}")
    public ResponseEntity<List<LivroEmprestimoProjection>> buscarEmprestimosPorLeitor(@PathVariable Long leitorId) {
        var emprestimos = livroEmprestimoService.buscarEmprestimosPorLeitor(leitorId);
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/emprestimos/livro/{livroId}")
    public ResponseEntity<List<LivroEmprestimoProjection>> buscarEmprestimosPorLivro(@PathVariable Long livroId) {
        var emprestimos = livroEmprestimoService.buscarEmprestimosPorLivro(livroId);
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/livros-mais-emprestados")
    public ResponseEntity<List<QuantidadeEmprestimosLivroProjection>> buscarEmprestimosPorLivro() {
        var ranking = livroEmprestimoService.buscarQuantidadeEmprestimosPorLivro();
        return ResponseEntity.ok(ranking);
    }
}