package com.turma2.biblioteca_api.services;

import com.turma2.biblioteca_api.exceptions.RecursoNaoEncontradoException;
import com.turma2.biblioteca_api.models.Leitor;
import com.turma2.biblioteca_api.repositories.LeitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeitorService {

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

    public Leitor buscarLeitorPorId(Long id) {
        return leitorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Leitor não encontrado para o ID " + id));
    }

    public List<Leitor> buscarLeitorPorNome(String nome) {
        return leitorRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Leitor buscarLeitorPorEmail(String email) {
        return leitorRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Leitor não encontrado para o e-mail: " + email));
    }
}