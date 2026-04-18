package com.example.biblioteca_api.service;

import com.example.biblioteca_api.model.Emprestimo;
import com.example.biblioteca_api.model.LivroEmprestimo;
import com.example.biblioteca_api.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;
    private final LeitorRepository leitorRepository;
    private final LivroEmprestimoRepository livroEmprestimoRepository;

    public EmprestimoService(
            EmprestimoRepository emprestimoRepository,
            LivroRepository livroRepository,
            LeitorRepository leitorRepository,
            LivroEmprestimoRepository livroEmprestimoRepository
    ){
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.leitorRepository = leitorRepository;
        this.livroEmprestimoRepository = livroEmprestimoRepository;
    }
    
    public Emprestimo salvar(Emprestimo emprestimo){
        return emprestimoRepository.save(emprestimo);
    }
    
    public List<Emprestimo> listar(){
        return emprestimoRepository.findAll();
    }

    public Emprestimo criarEmprestimo(
            Long leitorId,
            Long livroId,
            Integer quantidade,
            String dataEmprestimo,
            String dataDevolucao
    ){

        var leitor = leitorRepository.findById(leitorId)
                .orElseThrow(() -> new RuntimeException("Leitor não encontrado"));

        var livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolucao);
        emprestimo.setLeitor(leitor);

        emprestimo = emprestimoRepository.save(emprestimo);

        var livroEmprestimo = new LivroEmprestimo();
        livroEmprestimo.setLivro(livro);
        livroEmprestimo.setEmprestimo(emprestimo);
        livroEmprestimo.setQuantidade(quantidade);

        livroEmprestimoRepository.save(livroEmprestimo);

        return emprestimo;
    }
}