package com.turma2.biblioteca_api.services;

import com.turma2.biblioteca_api.controllers.request.LivroRequest;
import com.turma2.biblioteca_api.controllers.response.LivroResponse;
import com.turma2.biblioteca_api.exceptions.RecursoNaoEncontradoException;
import com.turma2.biblioteca_api.mappers.LivroMapper;
import com.turma2.biblioteca_api.models.Livro;
import com.turma2.biblioteca_api.repositories.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<LivroResponse> listarTodosOsLivros(Pageable pageable) {
        return livroRepository.findAll(pageable).map(livroMapper::entityToResponse);
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

    public LivroResponse editarLivro(Long id, @Valid LivroRequest livroRequest) {
        var livro = buscarLivroEntityPorId(id);
        livro.setTitulo(livroRequest.titulo());
        livro.setAutor(livroRequest.autor());
        livro.setEstoque(livroRequest.estoque());
        Livro livroAtualizado = livroRepository.save(livro);
        return livroMapper.entityToResponse(livroAtualizado);
    }

    public void excluirLivro(Long id) {
        Livro livro = buscarLivroEntityPorId(id);
        livroRepository.delete(livro);
    }
}