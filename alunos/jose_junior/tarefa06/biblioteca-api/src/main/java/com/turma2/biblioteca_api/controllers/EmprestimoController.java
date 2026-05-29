package com.turma2.biblioteca_api.controllers;

import com.turma2.biblioteca_api.controllers.request.EmprestimoRequest;
import com.turma2.biblioteca_api.controllers.response.EmprestimoResponse;
import com.turma2.biblioteca_api.services.EmprestimoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public ResponseEntity<Page<EmprestimoResponse>> listarTodosOsEmprestimos(Pageable pageable) {
        return ResponseEntity.ok(emprestimoService.listarTodosOsEmprestimos(pageable));
    }

    @PostMapping ResponseEntity<EmprestimoResponse> cadastrar(@RequestBody EmprestimoRequest emprestimoRequest) {
        EmprestimoResponse emprestimoResponse = emprestimoService.cadastrarEmprestimo(emprestimoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoResponse);
    }
}