package com.example.pedido_api.repository;

import com.example.pedido_api.model.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeitorRepository extends JpaRepository<Leitor, Long> {}