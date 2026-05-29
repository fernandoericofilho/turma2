package com.turma2.biblioteca_api.services;

import com.turma2.biblioteca_api.controllers.request.LeitorRequest;
import com.turma2.biblioteca_api.controllers.response.LeitorResponse;
import com.turma2.biblioteca_api.exceptions.RecursoNaoEncontradoException;
import com.turma2.biblioteca_api.mappers.LeitorMapper;
import com.turma2.biblioteca_api.models.Leitor;
import com.turma2.biblioteca_api.repositories.LeitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeitorService {

    private final LeitorRepository leitorRepository;
    private final LeitorMapper leitorMapper;

    public LeitorService(LeitorRepository repository, LeitorMapper leitorMapper) {
        this.leitorRepository = repository;
        this.leitorMapper = leitorMapper;
    }

    public LeitorResponse cadastrarLeitor(LeitorRequest leitorRequest) {
        var leitor = leitorMapper.requestToEntity(leitorRequest);
        leitorRepository.save(leitor);
        return leitorMapper.entityToResponse(leitor);
    }

    public List<LeitorResponse> listarTodosOsLeitores() {
        var leitores = leitorRepository.findAll();
        List<LeitorResponse> leitorResponses = leitores.stream()
                .map(leitor -> leitorMapper.entityToResponse(leitor))
                .toList();
        return leitorResponses;
    }

    public Leitor buscarLeitorEntityPorId(Long id) {
        return leitorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Leitor não encontrado para o ID " + id));
    }

    public LeitorResponse buscarLeitorPorId(Long id) {
        var leitor = buscarLeitorEntityPorId(id);
        return leitorMapper.entityToResponse(leitor);
    }

    public List<LeitorResponse> buscarLeitoresPorNome(String nome) {
        var leitores = leitorRepository.findByNomeContainingIgnoreCase(nome);
        List<LeitorResponse> leitoresResponse = leitores.stream()
                .map(leitor -> leitorMapper.entityToResponse(leitor))
                .toList();
        return leitoresResponse;
    }

    public LeitorResponse buscarLeitorPorEmail(String email) {
        var leitor = leitorRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Leitor não encontrado para o e-mail: " + email));
        return new LeitorResponse(
                leitor.getId(),
                leitor.getNome(),
                leitor.getEmail()
        );
    }
}