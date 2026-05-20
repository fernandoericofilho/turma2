package com.dev.sistema_biblioteca.controller;

import com.dev.sistema_biblioteca.controller.request.LivroRequest;
import com.dev.sistema_biblioteca.controller.response.LivroResponse;
import com.dev.sistema_biblioteca.dto.LivroDTO;
import com.dev.sistema_biblioteca.mapper.LivroMapper;
import com.dev.sistema_biblioteca.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<LivroResponse> cadastrar(@RequestBody @Valid LivroRequest request) {

        LivroDTO dto = mapper.requestToDto(request);
        LivroDTO dtoSalvo = livroService.cadastrar(dto);
        LivroResponse response = mapper.dtoToResponse(dtoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<LivroResponse>> buscarTodos(Pageable pageable) {

        Page<LivroDTO> pageDto = livroService.buscarTodos(pageable);

        Page<LivroResponse> response = pageDto
                .map(mapper::dtoToResponse);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> buscarPorId(@PathVariable Long id) {

        LivroDTO dto = livroService.buscarPorId(id);
        LivroResponse response = mapper.dtoToResponse(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/titulo")
    public ResponseEntity<List<LivroResponse>> buscarPorTitulo(@RequestParam String titulo) {

        List<LivroDTO> listaDto = livroService.buscarPorTitulo(titulo);

        List<LivroResponse> response = listaDto
                .stream()
                .map(mapper::dtoToResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> atualizar(@PathVariable Long id, @RequestBody @Valid LivroRequest request){
        LivroDTO dto = mapper.requestToDto(request);
        LivroDTO dtoAtualizado = livroService.atualizar(id, dto);

        LivroResponse response = mapper.dtoToResponse(dtoAtualizado);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
