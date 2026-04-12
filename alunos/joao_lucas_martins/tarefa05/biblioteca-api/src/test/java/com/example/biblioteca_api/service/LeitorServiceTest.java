package com.example.biblioteca_api.service;

import com.example.biblioteca_api.model.Leitor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeitorServiceTest{

    @Autowired
    private LeitorService service;

    @Test
    void deveSalvarLeitor(){

        Leitor leitor = new Leitor();
        leitor.setNome("Maria");
        leitor.setEmail("maria@email.com");

        Leitor salvo = service.salvar(leitor);

        assertNotNull(salvo.getId());
        assertEquals("Maria", salvo.getNome());
    }
}