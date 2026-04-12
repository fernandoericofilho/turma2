package com.example.biblioteca_api.service;

import com.example.biblioteca_api.model.Leitor;
import com.example.biblioteca_api.repository.LeitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeitorService {

    private final LeitorRepository repository;

    public LeitorService(LeitorRepository repository){
        this.repository = repository;
    }

    public Leitor salvar(Leitor leitor){
        return repository.save(leitor);
    }

    public List<Leitor> listar(){
        return repository.findAll();
    }
}