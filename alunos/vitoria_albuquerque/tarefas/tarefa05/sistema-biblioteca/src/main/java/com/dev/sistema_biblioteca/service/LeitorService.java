package com.dev.sistema_biblioteca.service;

import com.dev.sistema_biblioteca.entity.Leitor;
import com.dev.sistema_biblioteca.repository.LeitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeitorService {

    private final LeitorRepository leitorRepository;

    public final Leitor criar(Leitor leitor){
        return leitorRepository.save(leitor);
    }

    public final List<Leitor> buscarTodos(){
        return leitorRepository.findAll();
    }

}
