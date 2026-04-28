package com.example.pedido_api.service;

import com.example.pedido_api.model.Livro;
import com.example.pedido_api.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public Livro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public void diminuirEstoque(Livro livro, int quantidade) {
        if (livro.getEstoque() < quantidade) {
            throw new RuntimeException("Estoque insuficiente");
        }
        livro.setEstoque(livro.getEstoque() - quantidade);
    }
}