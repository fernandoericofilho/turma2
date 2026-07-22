package com.dev.sistema_biblioteca.service;

import com.dev.sistema_biblioteca.dto.LivroDTO;
import com.dev.sistema_biblioteca.entity.Livro;
import com.dev.sistema_biblioteca.entity.LivroEmprestimo;
import com.dev.sistema_biblioteca.exception.BusinessException;
import com.dev.sistema_biblioteca.exception.ResourceNotFoundException;
import com.dev.sistema_biblioteca.mapper.LivroMapper;
import com.dev.sistema_biblioteca.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private LivroMapper mapper;

    @InjectMocks
    private LivroService livroService;

    private Livro livro;
    private LivroDTO livroDTO;

    @BeforeEach
    void setup() {

        livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("Clean Code");
        livro.setAutor("Robert C. Martin");
        livro.setLivroEmprestimos(new ArrayList<>());

        livroDTO = new LivroDTO();
        livroDTO.setTitulo("Clean Code");
        livroDTO.setAutor("Robert C. Martin");
    }

    @Test
    void deveCadastrarLivroComSucesso() {

        when(mapper.dtoToEntity(livroDTO)).thenReturn(livro);
        when(livroRepository.save(livro)).thenReturn(livro);
        when(mapper.entityToDto(livro)).thenReturn(livroDTO);

        LivroDTO resultado = livroService.cadastrar(livroDTO);

        assertNotNull(resultado);
        assertEquals("Clean Code", resultado.getTitulo());
        assertEquals("Robert C. Martin", resultado.getAutor());

        verify(mapper).dtoToEntity(livroDTO);
        verify(livroRepository).save(livro);
        verify(mapper).entityToDto(livro);
    }

    @Test
    void deveLancarExcecaoAoAtualizarLivroInexistente() {

        when(livroRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> livroService.atualizar(1L, livroDTO));

        verify(livroRepository).findById(1L);
        verify(livroRepository, never()).save(any());
    }

    @Test
    void deveDeletarLivroComSucesso() {

        when(livroRepository.findById(1L))
                .thenReturn(Optional.of(livro));

        livroService.deletar(1L);

        verify(livroRepository).findById(1L);
        verify(livroRepository).delete(livro);
    }

    @Test
    void deveLancarBusinessExceptionQuandoLivroPossuiEmprestimos() {

        LivroEmprestimo emprestimo = new LivroEmprestimo();

        livro.setLivroEmprestimos(List.of(emprestimo));

        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));

        assertThrows(BusinessException.class, () -> livroService.deletar(1L));

        verify(livroRepository).findById(1L);
        verify(livroRepository, never()).delete(any());
    }
}