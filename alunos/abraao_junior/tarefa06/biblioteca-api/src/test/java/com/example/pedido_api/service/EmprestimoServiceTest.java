package com.example.pedido_api.service;

import com.example.pedido_api.dto.LivroDTO;
import com.example.pedido_api.model.Emprestimo;
import com.example.pedido_api.model.Leitor;
import com.example.pedido_api.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EmprestimoServiceTest {
    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private LeitorService leitorService;

    @Test
    void deveCriarEmprestimo() {

        LivroDTO dto = new LivroDTO();
        dto.setTitulo("Entendendo Algoritmos");
        dto.setAutor("Aditya Bhargava");
        dto.setEstoque(10);

        LivroDTO livroSalvo = livroService.cadastrar(dto);

        Leitor leitor = new Leitor();
        leitor.setNome("Joana");
        leitor.setEmail(UUID.randomUUID() + "@email.com");

        leitor = leitorService.salvar(leitor);

        Emprestimo emprestimo = emprestimoService.criar(
                leitor.getId(),
                Map.of(livroSalvo.getId(), 2)
        );

        assertNotNull(emprestimo.getId());

        assertEquals(leitor.getId(),
                emprestimo.getLeitor().getId());

        assertEquals(1,
                emprestimo.getLivros().size());
    }
}
