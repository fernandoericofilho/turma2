package br.com.jonathas.stashflow.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    private UUID id;

    @Column(name = "customer_id", nullable = false, length = 100)
    private String customerId;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected Payment() {
        // Construtor exigido pelo JPA.
    }

    private Payment(
            UUID id,
            String customerId,
            BigDecimal amount,
            PaymentStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Payment create(String customerId, BigDecimal amount) {
        validateCustomerId(customerId);
        validateAmount(amount);

        Instant now = Instant.now();

        return new Payment(
                UUID.randomUUID(),
                customerId.trim(),
                amount,
                PaymentStatus.PENDING,
                now,
                now
        );
    }

    public void approve() {
        ensurePending();
        this.status = PaymentStatus.APPROVED;
        this.updatedAt = Instant.now();
    }

    public void reject() {
        ensurePending();
        this.status = PaymentStatus.REJECTED;
        this.updatedAt = Instant.now();
    }

    private void ensurePending() {
        if (status != PaymentStatus.PENDING) {
            throw new IllegalStateException(
                    "Only pending payments can be processed"
            );
        }
    }

    private static void validateCustomerId(String customerId) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException(
                    "Customer ID must not be blank"
            );
        }

        if (customerId.trim().length() > 100) {
            throw new IllegalArgumentException(
                    "Customer ID must not exceed 100 characters"
            );
        }
    }

    private static void validateAmount(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException(
                    "Payment amount must be greater than zero"
            );
        }

        if (amount.scale() > 2) {
            throw new IllegalArgumentException(
                    "Payment amount must have at most two decimal places"
            );
        }
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}