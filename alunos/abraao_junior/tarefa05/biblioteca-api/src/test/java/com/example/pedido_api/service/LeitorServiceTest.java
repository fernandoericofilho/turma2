package com.example.pedido_api.service;

import com.example.pedido_api.model.Leitor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class LeitorServiceTest {

    @Autowired
    private LeitorService leitorService;

    @Test
    void deveCriarLeitor() {

        Leitor leitor = new Leitor();
        leitor.setNome("José Rodrigues");
        leitor.setEmail(UUID.randomUUID() + "@email.com");

        Leitor leitorSalvo = leitorService.salvar(leitor);

        assertNotNull(leitorSalvo.getId());

        assertEquals("José Rodrigues", leitorSalvo.getNome());
    }
}
