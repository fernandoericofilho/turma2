package com.example.biblioteca_api.service;

import com.example.biblioteca_api.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroServiceTest{

    @Autowired
    private LivroService service;

    @Test
    void deveSalvarLivro(){

        Livro livro = new Livro();
        livro.setAutor("Machado de Assis");
        livro.setTitulo("O guarani");

        Livro salvo = service.salvar(livro);

        assertNotNull(salvo.getId());
        assertEquals("Machado de Assis", salvo.getAutor());
        assertEquals("O guarani", salvo.getTitulo());
    }
}