package com.dev.sistema_biblioteca.repository;

import com.dev.sistema_biblioteca.entity.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeitorRepository extends JpaRepository<Leitor, Long> {
}
