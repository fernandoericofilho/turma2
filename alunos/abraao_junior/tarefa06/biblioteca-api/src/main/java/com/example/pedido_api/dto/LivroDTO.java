package com.example.pedido_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDTO {

    private Long id;

    private String titulo;

    private String autor;

    private Integer estoque;
}
