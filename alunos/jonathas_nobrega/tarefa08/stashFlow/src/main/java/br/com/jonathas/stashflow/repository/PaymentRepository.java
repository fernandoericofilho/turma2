package br.com.jonathas.stashflow.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jonathas.stashflow.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}