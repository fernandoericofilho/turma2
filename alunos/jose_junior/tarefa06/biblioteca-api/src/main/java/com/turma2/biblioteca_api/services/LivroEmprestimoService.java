package com.turma2.biblioteca_api.services;

import com.turma2.biblioteca_api.repositories.projections.LivroEmprestimoProjection;
import com.turma2.biblioteca_api.repositories.projections.QuantidadeEmprestimosLivroProjection;
import com.turma2.biblioteca_api.repositories.LivroEmprestimoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroEmprestimoService {

    private final LivroEmprestimoRepository livroEmprestimoRepository;

    public LivroEmprestimoService(LivroEmprestimoRepository repository) {
        this.livroEmprestimoRepository = repository;
    }

    public List<LivroEmprestimoProjection> buscarEmprestimosPorLeitor(Long leitorId) {
        return livroEmprestimoRepository.buscarEmprestimosPorLeitor(leitorId);
    }

    public List<LivroEmprestimoProjection> buscarEmprestimosPorLivro(Long livroId) {
        return livroEmprestimoRepository.buscarEmprestimosPorLivro(livroId);
    }

    public List<QuantidadeEmprestimosLivroProjection> buscarQuantidadeEmprestimosPorLivro() {
        return livroEmprestimoRepository.buscarQuantidadeEmprestimosPorLivro();
    }
}