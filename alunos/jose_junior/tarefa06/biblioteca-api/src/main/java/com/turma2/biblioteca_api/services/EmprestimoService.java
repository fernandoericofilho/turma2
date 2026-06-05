package com.turma2.biblioteca_api.services;

import com.turma2.biblioteca_api.controllers.request.EmprestimoRequest;
import com.turma2.biblioteca_api.controllers.response.EmprestimoResponse;
import com.turma2.biblioteca_api.exceptions.EstoqueInsuficienteException;
import com.turma2.biblioteca_api.mappers.EmprestimoMapper;
import com.turma2.biblioteca_api.models.Emprestimo;
import com.turma2.biblioteca_api.models.Leitor;
import com.turma2.biblioteca_api.models.Livro;
import com.turma2.biblioteca_api.models.LivroEmprestimo;
import com.turma2.biblioteca_api.repositories.EmprestimoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LeitorService leitorService;
    private final LivroService livroService;
    private final EmprestimoMapper emprestimoMapper;

    public EmprestimoService(EmprestimoRepository repository, LeitorService leitorService, LivroService livroService, EmprestimoMapper emprestimoMapper) {
        this.emprestimoRepository = repository;
        this.leitorService = leitorService;
        this.livroService = livroService;
        this.emprestimoMapper = emprestimoMapper;
    }

    @Transactional
    public EmprestimoResponse cadastrarEmprestimo(EmprestimoRequest emprestimoRequest) {
        if (emprestimoRequest.livrosIds().size() != emprestimoRequest.livrosIds().stream().distinct().count()) {
            throw new IllegalArgumentException("Não é permitido emprestar o mesmo livro mais de uma vez na mesma solicitação.");
        }

        Leitor leitor = leitorService.buscarLeitorEntityPorId(emprestimoRequest.leitorId());
        List<Livro> livros = emprestimoRequest.livrosIds()
                .stream()
                .map(livroService::buscarLivroEntityPorId)
                .toList();

        for (Livro livro : livros) {
            if (livro.getEstoque() <= 0) {
                throw new EstoqueInsuficienteException("Livro '" + livro.getTitulo() + "' sem estoque disponível.");
            }
            livro.setEstoque(livro.getEstoque() - 1);
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLeitor(leitor);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(emprestimoRequest.dataDevolucao());

        List<LivroEmprestimo> livrosEmprestimo = livros.stream()
                .map(livro -> {
                    LivroEmprestimo livemp = new LivroEmprestimo();
                    livemp.setLivro(livro);
                    livemp.setEmprestimo(emprestimo);
                    livemp.setQuantidade(1);
                    return livemp;
                }).toList();

        emprestimo.setLivros(livrosEmprestimo);
        emprestimoRepository.save(emprestimo);
        return emprestimoMapper.entityToResponse(emprestimo);
    }

    public Page<EmprestimoResponse> listarTodosOsEmprestimos(Pageable pageable) {
        return emprestimoRepository.findAll(pageable).map(emprestimoMapper::entityToResponse);
    }
}