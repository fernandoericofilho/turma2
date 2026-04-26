package com.example.pedido_api.service;

import com.example.pedido_api.model.Leitor;
import com.example.pedido_api.repository.LeitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeitorService {

    private final LeitorRepository repository;

    public Leitor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leitor não encontrado"));
    }

    public Leitor salvar(Leitor leitor) {
        return repository.save(leitor);
    }
}