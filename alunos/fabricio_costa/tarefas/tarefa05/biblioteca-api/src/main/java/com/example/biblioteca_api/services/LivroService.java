package com.example.biblioteca_api.services;

import com.example.biblioteca_api.dtos.LivroDTO;
import com.example.biblioteca_api.mappers.LivroMapper;
import com.example.biblioteca_api.models.Livro;
import com.example.biblioteca_api.repositories.LivroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsável pelas regras de negócio de Livro.
 *
 * IMPORTANTE:
 *  - Recebe e retorna sempre LivroDTO (nunca Entity, nunca Request/Response)
 *  - Usa o LivroMapper para converter entre DTO ↔ Entity
 *  - Nunca retorna Entity para o Controller
 */
@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper     livroMapper;

    public LivroService(LivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper     = livroMapper;
    }

    // ----------------------------------------------------------------
    // POST — Cadastrar livro
    // ----------------------------------------------------------------
    @Transactional
    public LivroDTO cadastrar(LivroDTO dto) {
        Livro entity  = livroMapper.toEntity(dto);   // DTO → Entity
        Livro salvo   = livroRepository.save(entity); // persiste
        return livroMapper.toDTO(salvo);              // Entity → DTO
    }

    // ----------------------------------------------------------------
    // GET (paginado) — Listar todos
    // ----------------------------------------------------------------
    @Transactional(readOnly = true)
    public Page<LivroDTO> listarTodos(Pageable pageable) {
        return livroRepository.findAll(pageable)
                .map(livroMapper::toDTO);             // Page<Entity> → Page<DTO>
    }

    // ----------------------------------------------------------------
    // GET por ID
    // ----------------------------------------------------------------
    @Transactional(readOnly = true)
    public LivroDTO buscarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                        "Livro não encontrado com id: " + id));
        return livroMapper.toDTO(livro);
    }

    // ----------------------------------------------------------------
    // GET por título
    // ----------------------------------------------------------------
    @Transactional(readOnly = true)
    public List<LivroDTO> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(livroMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ----------------------------------------------------------------
    // PUT — Atualizar livro
    // ----------------------------------------------------------------
    @Transactional
    public LivroDTO atualizar(Long id, LivroDTO dto) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                        "Livro não encontrado com id: " + id));

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());

        Livro atualizado = livroRepository.save(livro);
        return livroMapper.toDTO(atualizado);
    }

    // ----------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------
    @Transactional
    public void deletar(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new jakarta.persistence.EntityNotFoundException(
                    "Livro não encontrado com id: " + id);
        }
        livroRepository.deleteById(id);
    }
}