package com.turma2.biblioteca_api.services;

import com.turma2.biblioteca_api.controllers.request.LivroRequest;
import com.turma2.biblioteca_api.controllers.response.LivroResponse;
import com.turma2.biblioteca_api.exceptions.RecursoNaoEncontradoException;
import com.turma2.biblioteca_api.mappers.LivroMapper;
import com.turma2.biblioteca_api.models.Livro;
import com.turma2.biblioteca_api.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public LivroService(LivroRepository repository, LivroMapper mapper) {
        this.livroRepository = repository;
        this.livroMapper = mapper;
    }

    public LivroResponse cadastrarLivro(LivroRequest livroRequest) {
        var livro = livroMapper.requestToEntity(livroRequest);
        livroRepository.save(livro);
        return livroMapper.entityToResponse(livro);
    }

    public List<LivroResponse> listarTodosOsLivros() {
        var livros = livroRepository.findAll();
        List<LivroResponse> livrosResponse = livros.stream()
                .map(livro -> livroMapper.entityToResponse(livro))
                .toList();
        return livrosResponse;
    }

    public Livro buscarLivroEntityPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro não encontrado para o ID " + id));
    }

    public LivroResponse buscarLivroPorId(Long id) {
        var livro = buscarLivroEntityPorId(id);
        return livroMapper.entityToResponse(livro);
    }

    public List<LivroResponse> buscarLivrosPorTitulo(String titulo) {
        var livros =  livroRepository.findByTituloContainingIgnoreCase(titulo);
        List <LivroResponse> livrosResponse = livros.stream()
                .map(livro -> livroMapper.entityToResponse(livro))
                .toList();
        return livrosResponse;
    }

    public List<LivroResponse> buscarLivrosPorAutor(String autor) {
        var livros = livroRepository.findByAutorContainingIgnoreCase(autor);
        List <LivroResponse> livrosResponse = livros.stream()
                .map(livro -> livroMapper.entityToResponse(livro))
                .toList();
        return livrosResponse;
    }
}