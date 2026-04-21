package com.example.biblioteca_api.repositories;

import com.example.biblioteca_api.models.LivroEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroEmprestimoRepository extends JpaRepository<LivroEmprestimo, Long> {

    List<LivroEmprestimo> findByEmprestimoId(Long emprestimoId);

    List<LivroEmprestimo> findByLivroId(Long livroId);
}