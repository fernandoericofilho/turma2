package com.example.pedido_api.service;

import com.example.pedido_api.dto.LivroDTO;
import com.example.pedido_api.exception.BusinessException;
import com.example.pedido_api.exception.ResourceNotFoundException;
import com.example.pedido_api.mapper.LivroMapper;
import com.example.pedido_api.model.Livro;
import com.example.pedido_api.repository.LivroEmprestimoRepository;
import com.example.pedido_api.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    private final LivroMapper mapper;

    private final LivroEmprestimoRepository livroEmprestimoRepository;

    public LivroDTO cadastrar(LivroDTO dto) {

        Livro livro = mapper.toEntity(dto);

        Livro livroSalvo = repository.save(livro);

        return mapper.toDTO(livroSalvo);
    }

    public Page<LivroDTO> listar(Pageable pageable) {

        return repository.findAll(pageable)
                .map(mapper::toDTO);
    }

    public Livro buscarEntityPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Livro não encontrado"));
    }

    public LivroDTO buscarPorId(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Livro não encontrado"));

        return mapper.toDTO(livro);
    }

    public void diminuirEstoque(Long id, int quantidade) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Livro não encontrado"
                        )
                );

        if (livro.getEstoque() < quantidade) {
            throw new BusinessException(
                    "Estoque insuficiente"
            );
        }

        livro.setEstoque(livro.getEstoque() - quantidade);
        repository.save(livro);
    }

    public LivroDTO atualizar(Long id, LivroDTO dto) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Livro não encontrado"));

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());

        Livro atualizado = repository.save(livro);

        return mapper.toDTO(atualizado);
    }

    public void deletar(Long id) {

        if (livroEmprestimoRepository
                .existsByLivroId(id)) {

            throw new BusinessException(
                    "Livro possui empréstimos vinculados"
            );
        }

        repository.deleteById(id);
    }

    public Page<LivroDTO> buscarPorTitulo(
            String titulo,
            Pageable pageable
    ) {

        return repository
                .findByTituloContainingIgnoreCase(
                        titulo,
                        pageable
                )
                .map(mapper::toDTO);
    }
}