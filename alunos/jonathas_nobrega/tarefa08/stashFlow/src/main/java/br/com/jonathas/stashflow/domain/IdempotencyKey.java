package br.com.jonathas.stashflow.domain;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "idempotency_keys")
public class IdempotencyKey {

    @Id
    private UUID id;

    @Column(
            name = "idempotency_key",
            nullable = false,
            unique = true,
            length = 255
    )
    private String idempotencyKey;

    @Column(
            name = "request_hash",
            nullable = false,
            length = 64
    )
    private String requestHash;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected IdempotencyKey() {
        // Construtor exigido pelo JPA.
    }

    private IdempotencyKey(
            UUID id,
            String idempotencyKey,
            String requestHash,
            Payment payment,
            Instant createdAt
    ) {
        this.id = id;
        this.idempotencyKey = idempotencyKey;
        this.requestHash = requestHash;
        this.payment = payment;
        this.createdAt = createdAt;
    }

    public static IdempotencyKey create(
            String idempotencyKey,
            String requestHash,
            Payment payment
    ) {
        validateIdempotencyKey(idempotencyKey);
        validateRequestHash(requestHash);
        validatePayment(payment);

        return new IdempotencyKey(
                UUID.randomUUID(),
                idempotencyKey.trim(),
                requestHash,
                payment,
                Instant.now()
        );
    }

    private static void validateIdempotencyKey(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException(
                    "Idempotency key must not be blank"
            );
        }

        if (idempotencyKey.trim().length() > 255) {
            throw new IllegalArgumentException(
                    "Idempotency key must not exceed 255 characters"
            );
        }
    }

    private static void validateRequestHash(String requestHash) {
        if (requestHash == null || requestHash.isBlank()) {
            throw new IllegalArgumentException(
                    "Request hash must not be blank"
            );
        }

        if (requestHash.length() != 64) {
            throw new IllegalArgumentException(
                    "Request hash must contain 64 characters"
            );
        }
    }

    private static void validatePayment(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException(
                    "Payment must not be null"
            );
        }
    }

    public UUID getId() {
        return id;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getRequestHash() {
        return requestHash;
    }

    public Payment getPayment() {
        return payment;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}