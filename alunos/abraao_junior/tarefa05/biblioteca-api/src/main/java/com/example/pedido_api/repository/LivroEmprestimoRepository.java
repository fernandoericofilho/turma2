package com.example.pedido_api.repository;

import com.example.pedido_api.model.LivroEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroEmprestimoRepository extends JpaRepository<LivroEmprestimo, Long> {
}