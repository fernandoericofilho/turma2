package br.com.jonathas.stashflow.application.idempotency;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import org.springframework.stereotype.Component;

import br.com.jonathas.stashflow.dto.CreatePaymentRequest;

@Component
public class RequestHashGenerator {

    public String generate(CreatePaymentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException(
                    "Request must not be null"
            );
        }

        String customerId = normalizeCustomerId(request.customerId());
        String amount = normalizeAmount(request.amount());

        String canonicalRequest = buildCanonicalRequest(
                customerId,
                amount
        );

        return sha256(canonicalRequest);
    }

    private String normalizeCustomerId(String customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException(
                    "Customer ID must not be null"
            );
        }

        return customerId.trim();
    }

    private String normalizeAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException(
                    "Amount must not be null"
            );
        }

        return amount
                .stripTrailingZeros()
                .toPlainString();
    }

    private String buildCanonicalRequest(
            String customerId,
            String amount
    ) {
        return customerId.length()
                + ":"
                + customerId
                + "|"
                + amount;
    }

    private String sha256(String value) {
        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(
                    value.getBytes(StandardCharsets.UTF_8)
            );

            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException(
                    "SHA-256 algorithm is not available",
                    exception
            );
        }
    }
}