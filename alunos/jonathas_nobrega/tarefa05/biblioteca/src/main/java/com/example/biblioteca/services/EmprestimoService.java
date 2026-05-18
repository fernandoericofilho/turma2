package com.example.biblioteca.services;

import com.example.biblioteca.entity.*;
import com.example.biblioteca.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LeitorRepository leitorRepository;
    private final LivroRepository livroRepository;
    private final LivroEmprestimoRepository livroEmprestimoRepository;

    public EmprestimoService(
            EmprestimoRepository emprestimoRepository,
            LeitorRepository leitorRepository,
            LivroRepository livroRepository,
            LivroEmprestimoRepository livroEmprestimoRepository
    ) {
        this.emprestimoRepository = emprestimoRepository;
        this.leitorRepository = leitorRepository;
        this.livroRepository = livroRepository;
        this.livroEmprestimoRepository = livroEmprestimoRepository;
    }

    public Emprestimo criarEmprestimo(
            Long leitorId,
            Map<Long, Integer> livrosQuantidade
    ) {

        Leitor leitor = leitorRepository.findById(leitorId)
                .orElseThrow(() -> new RuntimeException("Leitor não encontrado"));

        Emprestimo emprestimo = new Emprestimo();

        emprestimo.setLeitor(leitor);
        emprestimo.setDataEmprestimo(LocalDate.now());

        emprestimo = emprestimoRepository.save(emprestimo);

        for (Map.Entry<Long, Integer> item : livrosQuantidade.entrySet()) {

            Livro livro = livroRepository.findById(item.getKey())
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

            LivroEmprestimo livroEmprestimo = new LivroEmprestimo();

            livroEmprestimo.setLivro(livro);
            livroEmprestimo.setEmprestimo(emprestimo);
            livroEmprestimo.setQuantidade(item.getValue());

            livroEmprestimoRepository.save(livroEmprestimo);
        }

        return emprestimo;
    }
}