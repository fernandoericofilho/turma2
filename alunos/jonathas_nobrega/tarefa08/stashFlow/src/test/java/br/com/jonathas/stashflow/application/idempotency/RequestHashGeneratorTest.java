package br.com.jonathas.stashflow.application.idempotency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.jonathas.stashflow.dto.CreatePaymentRequest;

class RequestHashGeneratorTest {

    private RequestHashGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new RequestHashGenerator();
    }

    @Test
    void shouldGenerateHashWith64Characters() {
        CreatePaymentRequest request = new CreatePaymentRequest(
                "cliente-123",
                new BigDecimal("150.50")
        );

        String hash = generator.generate(request);

        assertEquals(64, hash.length());
    }

    @Test
    void shouldGenerateSameHashForSameRequest() {
        CreatePaymentRequest firstRequest =
                new CreatePaymentRequest(
                        "cliente-123",
                        new BigDecimal("150.50")
                );

        CreatePaymentRequest secondRequest =
                new CreatePaymentRequest(
                        "cliente-123",
                        new BigDecimal("150.50")
                );

        String firstHash = generator.generate(firstRequest);
        String secondHash = generator.generate(secondRequest);

        assertEquals(firstHash, secondHash);
    }

    @Test
    void shouldIgnoreIrrelevantAmountScale() {
        CreatePaymentRequest firstRequest =
                new CreatePaymentRequest(
                        "cliente-123",
                        new BigDecimal("150.5")
                );

        CreatePaymentRequest secondRequest =
                new CreatePaymentRequest(
                        "cliente-123",
                        new BigDecimal("150.50")
                );

        String firstHash = generator.generate(firstRequest);
        String secondHash = generator.generate(secondRequest);

        assertEquals(firstHash, secondHash);
    }

    @Test
    void shouldIgnoreCustomerIdSurroundingSpaces() {
        CreatePaymentRequest firstRequest =
                new CreatePaymentRequest(
                        "cliente-123",
                        new BigDecimal("150.50")
                );

        CreatePaymentRequest secondRequest =
                new CreatePaymentRequest(
                        "  cliente-123  ",
                        new BigDecimal("150.50")
                );

        assertEquals(
                generator.generate(firstRequest),
                generator.generate(secondRequest)
        );
    }

    @Test
    void shouldGenerateDifferentHashForDifferentAmount() {
        CreatePaymentRequest firstRequest =
                new CreatePaymentRequest(
                        "cliente-123",
                        new BigDecimal("150.50")
                );

        CreatePaymentRequest secondRequest =
                new CreatePaymentRequest(
                        "cliente-123",
                        new BigDecimal("200.00")
                );

        assertNotEquals(
                generator.generate(firstRequest),
                generator.generate(secondRequest)
        );
    }

    @Test
    void shouldGenerateDifferentHashForDifferentCustomer() {
        CreatePaymentRequest firstRequest =
                new CreatePaymentRequest(
                        "cliente-123",
                        new BigDecimal("150.50")
                );

        CreatePaymentRequest secondRequest =
                new CreatePaymentRequest(
                        "cliente-999",
                        new BigDecimal("150.50")
                );

        assertNotEquals(
                generator.generate(firstRequest),
                generator.generate(secondRequest)
        );
    }
}