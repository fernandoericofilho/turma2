package com.turma2.biblioteca_api.services;

import com.turma2.biblioteca_api.models.Livro;
import com.turma2.biblioteca_api.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository repository) {
        this.livroRepository = repository;
    }

    public Livro cadastrarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> listarTodosOsLivros() {
        return livroRepository.findAll();
    }

    public List<Livro> buscarLivrosPorTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }

    public List<Livro> buscarLivrosPorAutor(String autor) {
        return livroRepository.findByAutor(autor);
    }
}