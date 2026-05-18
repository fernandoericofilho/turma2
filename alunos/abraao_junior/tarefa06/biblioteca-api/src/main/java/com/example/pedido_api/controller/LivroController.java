package com.example.pedido_api.controller;

import com.example.pedido_api.controller.request.LivroRequest;
import com.example.pedido_api.controller.response.LivroResponse;
import com.example.pedido_api.dto.LivroDTO;
import com.example.pedido_api.mapper.LivroMapper;
import com.example.pedido_api.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;

    private final LivroMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroResponse cadastrar(
            @RequestBody @Valid LivroRequest request
    ) {

        LivroDTO dto = mapper.toDTO(request);

        LivroDTO salvo = service.cadastrar(dto);

        return mapper.toResponse(salvo);
    }

    @GetMapping
    public Page<LivroResponse> listar(Pageable pageable) {

        return service.listar(pageable)
                .map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public LivroResponse buscarPorId(
            @PathVariable Long id
    ) {

        LivroDTO dto = service.buscarPorId(id);

        return mapper.toResponse(dto);
    }

    @GetMapping("/titulo")
    public Page<LivroResponse> buscarPorTitulo(
            @RequestParam String titulo,
            Pageable pageable
    ) {

        return service.buscarPorTitulo(
                        titulo,
                        pageable
                )
                .map(mapper::toResponse);
    }

    @PutMapping("/{id}")
    public LivroResponse atualizar(
            @PathVariable Long id,
            @RequestBody @Valid LivroRequest request
    ) {

        LivroDTO dto = mapper.toDTO(request);

        LivroDTO atualizado =
                service.atualizar(id, dto);

        return mapper.toResponse(atualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(
            @PathVariable Long id
    ) {

        service.deletar(id);
    }
}