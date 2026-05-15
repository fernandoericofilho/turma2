package com.turma2.biblioteca_api.services;

import com.turma2.biblioteca_api.dtos.CadastroEmprestimoDTO;
import com.turma2.biblioteca_api.models.Emprestimo;
import com.turma2.biblioteca_api.models.Leitor;
import com.turma2.biblioteca_api.models.Livro;
import com.turma2.biblioteca_api.models.LivroEmprestimo;
import com.turma2.biblioteca_api.repositories.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LeitorService leitorService;
    private final LivroService livroService;

    public EmprestimoService(EmprestimoRepository repository, LeitorService leitorService, LivroService livroService) {
        this.emprestimoRepository = repository;
        this.leitorService = leitorService;
        this.livroService = livroService;
    }

    public Emprestimo cadastrarEmprestimo(CadastroEmprestimoDTO cadastroEmprestimoDTO) {
        Leitor leitor = leitorService.buscarLeitorPorId(cadastroEmprestimoDTO.leitorId());
        List<Livro> livros = cadastroEmprestimoDTO.livrosIds()
                .stream()
                .map(livroService::buscarLivroPorId)
                .toList();
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLeitor(leitor);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(cadastroEmprestimoDTO.dataDevolucao());

        List<LivroEmprestimo> livrosEmprestimo = livros.stream()
                .map(livro -> {
                    LivroEmprestimo livemp = new LivroEmprestimo();
                    livemp.setLivro(livro);
                    livemp.setEmprestimo(emprestimo);
                    livemp.setQuantidade(1);
                    return livemp;
                }).toList();

        emprestimo.setLivros(livrosEmprestimo);
        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarTodosOsEmprestimos() {
        return emprestimoRepository.findAll();
    }
}