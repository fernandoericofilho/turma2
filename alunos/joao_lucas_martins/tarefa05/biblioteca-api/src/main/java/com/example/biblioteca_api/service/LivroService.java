package com.example.biblioteca_api.service;

import com.example.biblioteca_api.model.Livro;
import com.example.biblioteca_api.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository){
        this.repository = repository;
    }

    public Livro salvar(Livro livro){
        return repository.save(livro);
    }

    public List<Livro> listar() {
        return repository.findAll();
    }
}
