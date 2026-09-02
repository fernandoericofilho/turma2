package br.com.jonathas.stashflow.application.command;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jonathas.stashflow.domain.IdempotencyKey;
import br.com.jonathas.stashflow.domain.Payment;
import br.com.jonathas.stashflow.dto.CreatePaymentRequest;
import br.com.jonathas.stashflow.dto.PaymentResponse;
import br.com.jonathas.stashflow.repository.IdempotencyKeyRepository;
import br.com.jonathas.stashflow.repository.PaymentRepository;

@Service
public class PaymentCreationTransactionService {

    private final PaymentRepository paymentRepository;
    private final IdempotencyKeyRepository idempotencyKeyRepository;

    public PaymentCreationTransactionService(
            PaymentRepository paymentRepository,
            IdempotencyKeyRepository idempotencyKeyRepository
    ) {
        this.paymentRepository = paymentRepository;
        this.idempotencyKeyRepository = idempotencyKeyRepository;
    }

    @Transactional
    public PaymentResponse create(
            String idempotencyKey,
            String requestHash,
            CreatePaymentRequest request
    ) {
        Payment payment = Payment.create(
                request.customerId(),
                request.amount()
        );

        Payment savedPayment = paymentRepository.save(payment);

        IdempotencyKey keyRecord = IdempotencyKey.create(
                idempotencyKey,
                requestHash,
                savedPayment
        );

        idempotencyKeyRepository.saveAndFlush(keyRecord);

        return PaymentResponse.from(savedPayment);
    }
}