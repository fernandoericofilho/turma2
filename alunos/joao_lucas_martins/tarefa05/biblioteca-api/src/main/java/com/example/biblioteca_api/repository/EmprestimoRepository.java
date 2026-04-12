package com.example.biblioteca_api.repository;

import com.example.biblioteca_api.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
}