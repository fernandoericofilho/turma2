package com.example.pedido_api.service;

import com.example.pedido_api.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class LivroServiceTest {
    @Autowired
    private LivroService livroService;

    @Test
    void deveCriarLivro() {

        Livro livro = new Livro();
        livro.setTitulo("Entendendo Algoritmos");
        livro.setAutor("Aditya Bhargava");
        livro.setEstoque(10);

        Livro livroSalvo = livroService.salvar(livro);

        assertNotNull(livroSalvo.getId());

        assertEquals("Entendendo Algoritmos", livroSalvo.getTitulo());

        assertEquals(10, livroSalvo.getEstoque());
    }

    @Test
    void deveLancarErroQuandoEstoqueForInsuficiente() {

        Livro livro = new Livro();

        livro.setTitulo("O Programador Apaixonado");
        livro.setAutor("Chad Fowler");
        livro.setEstoque(3);

        Livro livroSalvo = livroService.salvar(livro);

        RuntimeException erro = assertThrows(
                RuntimeException.class,
                () -> livroService.diminuirEstoque(livroSalvo, 5)
        );

        assertEquals("Estoque insuficiente", erro.getMessage());
    }
}
