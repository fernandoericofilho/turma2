package com.turma2.biblioteca_api.services;

import com.turma2.biblioteca_api.models.Leitor;
import com.turma2.biblioteca_api.models.Livro;
import com.turma2.biblioteca_api.repositories.LeitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeitorService {
    @Autowired
    private final LeitorRepository leitorRepository;

    public LeitorService(LeitorRepository repository) {
        this.leitorRepository = repository;
    }

    public Leitor cadastrarLeitor(Leitor leitor) {
        return leitorRepository.save(leitor);
    }

    public List<Leitor> listarTodosOsLeitores() {
        return leitorRepository.findAll();
    }

    public List<Leitor> buscarLeitorPorNome(String nome) {
        return leitorRepository.findByNome(nome);
    }

    public Leitor buscarLeitorPorEmail(String email) {
        return leitorRepository.findByEmail(email);
    }
}