package com.example.biblioteca_api.repository;

import com.example.biblioteca_api.model.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeitorRepository extends JpaRepository<Leitor, Long> {

}