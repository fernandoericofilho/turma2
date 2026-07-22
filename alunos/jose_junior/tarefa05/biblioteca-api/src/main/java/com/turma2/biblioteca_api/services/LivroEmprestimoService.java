package com.turma2.biblioteca_api.services;

import com.turma2.biblioteca_api.models.Livro;
import com.turma2.biblioteca_api.repositories.LivroEmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroEmprestimoService {

    @Autowired
    private final LivroEmprestimoRepository livroEmprestimoRepository;

    public LivroEmprestimoService(LivroEmprestimoRepository repository) {
        this.livroEmprestimoRepository = repository;
    }

    public List<Livro> buscarEmprestimosPorLeitor(Long leitorId) {
        return livroEmprestimoRepository.buscarEmprestimosPorLeitor(leitorId);
    }

    public List<Livro> buscarEmprestimosPorLivro(Long livroId) {
        return livroEmprestimoRepository.buscarEmprestimosPorLivro(livroId);
    }

    public List<Livro> buscarQuantidadeEmprestimosPorLivro() {
        return livroEmprestimoRepository.buscarQuantidadeEmprestimosPorLivro();
    }
}