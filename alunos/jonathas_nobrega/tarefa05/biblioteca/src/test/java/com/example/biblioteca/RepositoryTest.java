package com.example.biblioteca;

import com.example.biblioteca.entity.Livro;
import com.example.biblioteca.repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Test
    void createLivro() {

        Livro livro = new Livro();
        livro.setTitulo("title test");
        livro.setAutor("title author");

        Livro livroSalvo = livroRepository.save(livro);

        assertNotNull(livroSalvo.getId());
    }
}