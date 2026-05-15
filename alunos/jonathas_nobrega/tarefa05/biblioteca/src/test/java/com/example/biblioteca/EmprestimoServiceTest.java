package com.example.biblioteca;

import com.example.biblioteca.entity.Leitor;
import com.example.biblioteca.entity.Livro;
import com.example.biblioteca.entity.Emprestimo;
import com.example.biblioteca.repository.LeitorRepository;
import com.example.biblioteca.repository.LivroRepository;
import com.example.biblioteca.service.EmprestimoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EmprestimoServiceTest {

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LeitorRepository leitorRepository;

    @Test
    void deveCriarEmprestimo() {

        Livro livro = new Livro();
        livro.setTitulo("TestBook");
        livro.setAutor("Mr. Test");

        livro = livroRepository.save(livro);

        Leitor leitor = new Leitor();
        leitor.setNome("JohnTest");
        leitor.setEmail("johntest@email.com");

        leitor = leitorRepository.save(leitor);

        Map<Long, Integer> livros = new HashMap<>();

        livros.put(livro.getId(), 2);

        Emprestimo emprestimo =
                emprestimoService.criarEmprestimo(
                        leitor.getId(),
                        livros
                );

        assertNotNull(emprestimo.getId());
    }
}