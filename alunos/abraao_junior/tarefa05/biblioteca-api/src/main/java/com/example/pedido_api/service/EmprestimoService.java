package com.example.pedido_api.service;

import com.example.pedido_api.model.Emprestimo;
import com.example.pedido_api.model.Leitor;
import com.example.pedido_api.model.Livro;
import com.example.pedido_api.model.LivroEmprestimo;
import com.example.pedido_api.repository.EmprestimoRepository;
import com.example.pedido_api.service.LeitorService;
import com.example.pedido_api.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroService livroService;
    private final LeitorService leitorService;

    public Emprestimo criar(Long leitorId, Map<Long, Integer> livros) {

        Leitor leitor = leitorService.buscarPorId(leitorId);

        Emprestimo emp = new Emprestimo();
        emp.setLeitor(leitor);

        LocalDate hoje = LocalDate.now();
        emp.setDataEmprestimo(LocalDate.now());
        emp.setDataDevolucao(hoje.plusDays(15));

        List<LivroEmprestimo> lista = new ArrayList<>();

        for (var entry : livros.entrySet()) {

            Livro livro = livroService.buscarPorId(entry.getKey());

            livroService.diminuirEstoque(livro, entry.getValue());

            LivroEmprestimo le = new LivroEmprestimo();
            le.setLivro(livro);
            le.setEmprestimo(emp);
            le.setQuantidade(entry.getValue());

            lista.add(le);
        }

        emp.setLivros(lista);

        return emprestimoRepository.save(emp);
    }
}