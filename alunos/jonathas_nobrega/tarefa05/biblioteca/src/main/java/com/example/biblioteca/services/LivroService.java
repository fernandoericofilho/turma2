package com.example.biblioteca.services;

import com.example.biblioteca.dtos.LivroDTO;
import com.example.biblioteca.entity.Livro;
import com.example.biblioteca.mappers.LivroMapper;
import com.example.biblioteca.repository.LivroRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public LivroService(
            LivroRepository livroRepository,
            LivroMapper livroMapper
    ) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }

    public LivroDTO cadastrar(LivroDTO dto) {

        Livro livro = livroMapper.toEntity(dto);

        livro = livroRepository.save(livro);

        return livroMapper.toDTO(livro);
    }

    public Page<LivroDTO> listar(Pageable pageable) {

        return livroRepository
                .findAll(pageable)
                .map(livroMapper::toDTO);
    }

    public LivroDTO buscarPorId(Long id) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Livro não encontrado"));

        return livroMapper.toDTO(livro);
    }

    public LivroDTO atualizar(Long id, LivroDTO dto) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Livro não encontrado"));

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());

        livro = livroRepository.save(livro);

        return livroMapper.toDTO(livro);
    }

    public void deletar(Long id) {

        livroRepository.deleteById(id);
    }
}