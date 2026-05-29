package com.dev.sistema_biblioteca.service;

import com.dev.sistema_biblioteca.dto.LivroDTO;
import com.dev.sistema_biblioteca.entity.Livro;
import com.dev.sistema_biblioteca.exception.BusinessException;
import com.dev.sistema_biblioteca.exception.ResourceNotFoundException;
import com.dev.sistema_biblioteca.mapper.LivroMapper;
import com.dev.sistema_biblioteca.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper mapper;

    public LivroDTO cadastrar(LivroDTO dto) {

        Livro livro = mapper.dtoToEntity(dto);
        Livro livroSalvo = livroRepository.save(livro);
        return mapper.entityToDto(livroSalvo);
    }

    public Page<LivroDTO> buscarTodos(Pageable pageable) {

        Page<Livro> pageLivros = livroRepository.findAll(pageable);
        return pageLivros.map(mapper::entityToDto);
    }

    public LivroDTO buscarPorId(Long id){
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado."));
        return mapper.entityToDto(livro);
    }

    public List<LivroDTO> buscarPorTitulo(String titulo){
        return livroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(mapper::entityToDto)
                .toList();
    }

    public LivroDTO atualizar(Long id, LivroDTO dto) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado."));

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());

        Livro livroAtualizado = livroRepository.save(livro);

        return mapper.entityToDto(livroAtualizado);
    }

    public void deletar(Long id){
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado."));

        if (!livro.getLivroEmprestimos().isEmpty()) {
            throw new BusinessException(
                    "Livro possui empréstimos vinculados."
            );
        }

        livroRepository.delete(livro);
    }
}
