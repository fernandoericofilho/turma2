package com.example.biblioteca_api.repositories;

import com.example.biblioteca_api.models.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeitorRepository extends JpaRepository<Leitor, Long> {

    // Buscar leitor pelo e-mail
    Optional<Leitor> findByEmail(String email);

    // Verificar se já existe leitor com o e-mail
    boolean existsByEmail(String email);
}