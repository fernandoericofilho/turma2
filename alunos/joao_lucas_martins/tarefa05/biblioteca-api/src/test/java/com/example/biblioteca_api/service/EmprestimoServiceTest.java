package com.example.biblioteca_api.service;

import com.example.biblioteca_api.model.Livro;
import com.example.biblioteca_api.model.Leitor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmprestimoServiceTest{

    @Autowired
    private EmprestimoService service;

    @Autowired
    private LeitorService leitorService;

    @Autowired
    private LivroService livroService;

    @Test
    void deveRegistrarEmprestimo(){

        Leitor leitor = new Leitor();
        leitor.setNome("Carlos");
        leitor.setEmail("carlos@email.com");
        leitor = leitorService.salvar(leitor);

        Livro livro = new Livro();
        livro.setTitulo("Clean Code");
        livro.setAutor("Robert Martin");
        livro = livroService.salvar(livro);

        var emprestimo = service.criarEmprestimo(
               leitor.getId(),
                livro.getId(),
                2,
                "2026-04-10",
                "2026-04-20"
        );

        assertNotNull(emprestimo.getId());
        assertEquals(leitor.getId(), emprestimo.getLeitor().getId());
    }

}
