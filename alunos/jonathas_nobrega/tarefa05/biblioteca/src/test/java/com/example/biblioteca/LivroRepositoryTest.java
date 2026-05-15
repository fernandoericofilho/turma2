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
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LeitorRepository leitorRepository;

    @Autowired
    private EmprestimoService emprestimoService;

    @Test
    void deveBuscarLivrosPorLeitor() {

        Livro livro = new Livro();
        livro.setTitulo("testBook");
        livro.setAutor("Mr.Test");

        livro = livroRepository.save(livro);

        Leitor leitor = new Leitor();
        leitor.setNome("John Test");
        leitor.setEmail("johntest@email.com");

        leitor = leitorRepository.save(leitor);

        Map<Long, Integer> livros = new HashMap<>();

        livros.put(livro.getId(), 1);

        emprestimoService.criarEmprestimo(
                leitor.getId(),
                livros
        );

        List<Livro> livrosEncontrados =
                livroRepository.buscarLivrosPorLeitor(leitor.getId());

        assertEquals(1, livrosEncontrados.size());
    }

    @Test
    void deveContarEmprestimosPorLivro() {

        Livro livro = new Livro();
        livro.setTitulo("BookTest");
        livro.setAutor("Mr. Test");

        livro = livroRepository.save(livro);

        Leitor leitor = new Leitor();
        leitor.setNome("John Test");
        leitor.setEmail("johntest@email.com");

        leitor = leitorRepository.save(leitor);

        Map<Long, Integer> livros = new HashMap<>();
        livros.put(livro.getId(), 1);

        emprestimoService.criarEmprestimo(
                leitor.getId(),
                livros
        );

        List<Object[]> resultado =
                livroRepository.contarEmprestimosPorLivro();

        for (Object[] linha : resultado) {

            System.out.println(
                    linha[0] + " -> " + linha[1]
            );
        }

        assertEquals(1, resultado.size());
    }
}