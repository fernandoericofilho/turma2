package com.example.pedido_api.repository;

import com.example.pedido_api.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByLeitorId(Long leitorId);
}