package com.dev.sistema_biblioteca.service;

import com.dev.sistema_biblioteca.entity.Emprestimo;
import com.dev.sistema_biblioteca.entity.Leitor;
import com.dev.sistema_biblioteca.repository.EmprestimoRepository;
import com.dev.sistema_biblioteca.repository.LeitorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LeitorRepository leitorRepository;

    public Emprestimo criar(Long leitorId, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao) {

        Leitor leitor = leitorRepository.findById(leitorId)
                .orElseThrow(() -> new EntityNotFoundException("Leitor não encontrado."));

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLeitor(leitor);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolucao);

        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> buscarTodos(){
        return emprestimoRepository.findAll();
    }
}
