package br.com.jonathas.stashflow.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jonathas.stashflow.domain.IdempotencyKey;

public interface IdempotencyKeyRepository
        extends JpaRepository<IdempotencyKey, UUID> {

    @Query("""
        SELECT idempotency
        FROM IdempotencyKey idempotency
        JOIN FETCH idempotency.payment
        WHERE idempotency.idempotencyKey = :key
        """)
    Optional<IdempotencyKey> findByKeyWithPayment(
            @Param("key") String key
    );
}