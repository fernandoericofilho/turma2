package com.turma2.biblioteca_api.controllers;

import com.turma2.biblioteca_api.controllers.request.LeitorRequest;
import com.turma2.biblioteca_api.controllers.response.LeitorResponse;
import com.turma2.biblioteca_api.services.LeitorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leitores")
public class LeitorController {

    private final LeitorService leitorService;

    public LeitorController(LeitorService leitorService) {
        this.leitorService = leitorService;
    }

    @GetMapping
    public ResponseEntity<List<LeitorResponse>> listarTodosOsLeitores() {
        var leitores = leitorService.listarTodosOsLeitores();
        return ResponseEntity.ok(leitores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeitorResponse> buscarLeitorPorId(@PathVariable Long id) {
        LeitorResponse leitorResponse = leitorService.buscarLeitorPorId(id);
        return ResponseEntity.ok(leitorResponse);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<LeitorResponse>> buscarLeitoresPorNome(@RequestParam String nome) {
        var leitores = leitorService.buscarLeitoresPorNome(nome);
        return ResponseEntity.ok(leitores);
    }

    @GetMapping("/email")
    public ResponseEntity<LeitorResponse> buscarLeitorPorEmail(@RequestParam String email) {
        LeitorResponse leitorResponse = leitorService.buscarLeitorPorEmail(email);
        return ResponseEntity.ok(leitorResponse);
    }

    @PostMapping
    public ResponseEntity<LeitorResponse> cadastrar(@RequestBody @Valid LeitorRequest leitorRequest) {
        LeitorResponse leitorResponse = leitorService.cadastrarLeitor(leitorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(leitorResponse);
    }
}