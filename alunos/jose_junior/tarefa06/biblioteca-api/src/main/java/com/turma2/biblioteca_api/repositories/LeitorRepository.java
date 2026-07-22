package com.turma2.biblioteca_api.repositories;

import com.turma2.biblioteca_api.models.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeitorRepository extends JpaRepository<Leitor, Long> {

    List<Leitor> findByNomeContainingIgnoreCase(String nome);

    Optional<Leitor> findByEmail(String email);
}