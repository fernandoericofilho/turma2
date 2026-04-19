package com.dev.sistema_biblioteca.service;

import com.dev.sistema_biblioteca.entity.Emprestimo;
import com.dev.sistema_biblioteca.entity.Livro;
import com.dev.sistema_biblioteca.entity.LivroEmprestimo;
import com.dev.sistema_biblioteca.repository.EmprestimoRepository;
import com.dev.sistema_biblioteca.repository.LivroEmprestimoRepository;
import com.dev.sistema_biblioteca.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroEmprestimoService {

    private final LivroEmprestimoRepository livroEmprestimoRepository;
    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;

    public LivroEmprestimo adicionar(Long livroId, Long emprestimoId, Integer quantidade){

        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new EntityNotFoundException("Empréstimo não encontrado."));

        LivroEmprestimo livroEmprestimo = new LivroEmprestimo();

        livroEmprestimo.setLivro(livro);
        livroEmprestimo.setEmprestimo(emprestimo);
        livroEmprestimo.setQuantidade(quantidade);

        return livroEmprestimoRepository.save(livroEmprestimo);
    };

    public List<Livro> buscarLivrosPorLeitor(Long leitorId){
        return livroEmprestimoRepository.buscarLivrosPorLeitor(leitorId);
    };

    public List<Object[]> contarEmprestimosPorLivro(){
        return livroEmprestimoRepository.contarEmprestimosPorLivro();
    }
}
