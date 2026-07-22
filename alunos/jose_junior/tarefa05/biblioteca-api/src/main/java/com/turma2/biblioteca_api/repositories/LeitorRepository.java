package com.turma2.biblioteca_api.repositories;

import com.turma2.biblioteca_api.models.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeitorRepository extends JpaRepository<Leitor, Long> {
    List<Leitor> findByNome(String nome);

    Leitor findByEmail(String email);
}