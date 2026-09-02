package br.com.jonathas.stashflow.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jonathas.stashflow.application.command.PaymentCommandService;
import br.com.jonathas.stashflow.application.query.PaymentQueryService;
import br.com.jonathas.stashflow.dto.CreatePaymentRequest;
import br.com.jonathas.stashflow.dto.PaymentResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;

    public PaymentController(
            PaymentCommandService paymentCommandService,
            PaymentQueryService paymentQueryService
    ) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(
            @RequestHeader("Idempotency-Key")
            String idempotencyKey,

            @Valid @RequestBody
            CreatePaymentRequest request
    ) {
        PaymentResponse payment =
                paymentCommandService.createPayment(
                        idempotencyKey,
                        request
                );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(payment.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(payment);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> findAll() {
        List<PaymentResponse> payments = paymentQueryService.findAll();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> findById(
            @PathVariable UUID id
    ) {
        PaymentResponse payment = paymentQueryService.findById(id);

        return ResponseEntity.ok(payment);
    }
}