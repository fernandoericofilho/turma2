package com.dev.sistema_biblioteca.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LivroResponse {
    private Long id;
    private String titulo;
    private String autor;
}
