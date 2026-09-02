package br.com.jonathas.stashflow.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import br.com.jonathas.stashflow.domain.Payment;
import br.com.jonathas.stashflow.domain.PaymentStatus;

public record PaymentResponse(
        UUID id,
        String customerId,
        BigDecimal amount,
        PaymentStatus status,
        Instant createdAt,
        Instant updatedAt
) {

    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getCustomerId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getCreatedAt(),
                payment.getUpdatedAt()
        );
    }
}