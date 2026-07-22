package com.turma2.biblioteca_api.services;

import com.turma2.biblioteca_api.models.Emprestimo;
import com.turma2.biblioteca_api.repositories.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmprestimoService {

    @Autowired
    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository repository) {
        this.emprestimoRepository = repository;
    }

    public Emprestimo cadastrarEmprestimo(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarTodosOsEmprestimos() {
        return emprestimoRepository.findAll();
    }
}