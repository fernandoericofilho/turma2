package com.example.biblioteca.config;

import com.example.biblioteca.entity.Leitor;
import com.example.biblioteca.entity.Livro;
import com.example.biblioteca.entity.Emprestimo;
import com.example.biblioteca.repository.LeitorRepository;
import com.example.biblioteca.repository.LivroRepository;
import com.example.biblioteca.service.EmprestimoService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TestDataRunner implements CommandLineRunner {

    private final LivroRepository livroRepository;
    private final LeitorRepository leitorRepository;
    private final EmprestimoService emprestimoService;

    public TestDataRunner(
            LivroRepository livroRepository,
            LeitorRepository leitorRepository,
            EmprestimoService emprestimoService
    ) {
        this.livroRepository = livroRepository;
        this.leitorRepository = leitorRepository;
        this.emprestimoService = emprestimoService;
    }

    @Override
    public void run(String... args) {

        Livro livro = new Livro();
        livro.setTitulo("Temp Book");
        livro.setAutor("Temp Man");

        livro = livroRepository.save(livro);

        Leitor leitor = new Leitor();
        leitor.setNome("John Doe");
        leitor.setEmail("johndoe@email.com");

        leitor = leitorRepository.save(leitor);

        Map<Long, Integer> livros = new HashMap<>();

        livros.put(livro.getId(), 3);

        Emprestimo emprestimo =
                emprestimoService.criarEmprestimo(
                        leitor.getId(),
                        livros
                );
    }
}