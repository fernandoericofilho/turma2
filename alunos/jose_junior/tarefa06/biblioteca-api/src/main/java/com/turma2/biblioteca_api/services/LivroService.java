package com.turma2.biblioteca_api.services;

import com.turma2.biblioteca_api.exceptions.RecursoNaoEncontradoException;
import com.turma2.biblioteca_api.models.Livro;
import com.turma2.biblioteca_api.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

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

    public Livro buscarLivroPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro não encontrado para o ID " + id));
    }

    public List<Livro> buscarLivrosPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> buscarLivrosPorAutor(String autor) {
        return livroRepository.findByAutorContainingIgnoreCase(autor);
    }
}