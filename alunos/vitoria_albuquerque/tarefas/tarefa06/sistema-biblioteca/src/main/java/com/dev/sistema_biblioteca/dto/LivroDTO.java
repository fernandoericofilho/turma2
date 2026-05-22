package com.dev.sistema_biblioteca.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LivroDTO {
    private Long id;
    private String titulo;
    private String autor;
}