package com.example.pedido_api.service;

import com.example.pedido_api.dto.LivroDTO;
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

        LivroDTO dto = new LivroDTO();
        dto.setTitulo("Entendendo Algoritmos");
        dto.setAutor("Aditya Bhargava");
        dto.setEstoque(10);

        LivroDTO livroSalvo = livroService.cadastrar(dto);

        assertNotNull(livroSalvo.getId());

        assertEquals("Entendendo Algoritmos", livroSalvo.getTitulo());

        assertEquals(10, livroSalvo.getEstoque());
    }

    @Test
    void deveLancarErroQuandoEstoqueForInsuficiente() {

        LivroDTO dto = new LivroDTO();

        dto.setTitulo("O Programador Apaixonado");
        dto.setAutor("Chad Fowler");
        dto.setEstoque(3);

        LivroDTO livroSalvo = livroService.cadastrar(dto);

        RuntimeException erro = assertThrows(
                RuntimeException.class,
                () -> livroService.diminuirEstoque(livroSalvo.getId(), 5)
        );

        assertEquals("Estoque insuficiente", erro.getMessage());
    }
}
