package br.com.jonathas.stashflow.application.command;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jonathas.stashflow.application.idempotency.RequestHashGenerator;
import br.com.jonathas.stashflow.domain.IdempotencyKey;
import br.com.jonathas.stashflow.dto.CreatePaymentRequest;
import br.com.jonathas.stashflow.dto.PaymentResponse;
import br.com.jonathas.stashflow.exception.IdempotencyConflictException;
import br.com.jonathas.stashflow.repository.IdempotencyKeyRepository;

@Service
public class PaymentCommandService {

    private static final int MAX_KEY_LENGTH = 255;

    private final RequestHashGenerator requestHashGenerator;
    private final IdempotencyKeyRepository idempotencyKeyRepository;
    private final PaymentCreationTransactionService transactionService;

    public PaymentCommandService(
            RequestHashGenerator requestHashGenerator,
            IdempotencyKeyRepository idempotencyKeyRepository,
            PaymentCreationTransactionService transactionService
    ) {
        this.requestHashGenerator = requestHashGenerator;
        this.idempotencyKeyRepository = idempotencyKeyRepository;
        this.transactionService = transactionService;
    }

    public PaymentResponse createPayment(
            String idempotencyKey,
            CreatePaymentRequest request
    ) {
        String normalizedKey =
                normalizeIdempotencyKey(idempotencyKey);

        String requestHash =
                requestHashGenerator.generate(request);

        Optional<IdempotencyKey> existing =
                idempotencyKeyRepository.findByKeyWithPayment(
                        normalizedKey
                );

        if (existing.isPresent()) {
            return reuseExistingPayment(
                    existing.get(),
                    requestHash
            );
        }

        try {
            return transactionService.create(
                    normalizedKey,
                    requestHash,
                    request
            );
        } catch (DataIntegrityViolationException exception) {
            return recoverFromConcurrentCreation(
                    normalizedKey,
                    requestHash,
                    exception
            );
        }
    }

    private PaymentResponse recoverFromConcurrentCreation(
            String idempotencyKey,
            String requestHash,
            DataIntegrityViolationException originalException
    ) {
        Optional<IdempotencyKey> existing =
                idempotencyKeyRepository.findByKeyWithPayment(
                        idempotencyKey
                );

        if (existing.isEmpty()) {
            throw originalException;
        }

        return reuseExistingPayment(
                existing.get(),
                requestHash
        );
    }

    private PaymentResponse reuseExistingPayment(
            IdempotencyKey existing,
            String requestHash
    ) {
        if (!existing.getRequestHash().equals(requestHash)) {
            throw new IdempotencyConflictException();
        }

        return PaymentResponse.from(existing.getPayment());
    }

    private String normalizeIdempotencyKey(
            String idempotencyKey
    ) {
        if (idempotencyKey == null
                || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException(
                    "Idempotency-Key header must not be blank"
            );
        }

        String normalizedKey = idempotencyKey.trim();

        if (normalizedKey.length() > MAX_KEY_LENGTH) {
            throw new IllegalArgumentException(
                    "Idempotency-Key header must not exceed 255 characters"
            );
        }

        return normalizedKey;
    }
}