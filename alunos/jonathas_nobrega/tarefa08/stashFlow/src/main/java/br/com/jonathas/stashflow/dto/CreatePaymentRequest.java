package br.com.jonathas.stashflow.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePaymentRequest(

        @NotBlank(message = "Customer ID is required")
        @Size(max = 100, message = "Customer ID must not exceed 100 characters")
        String customerId,

        @NotNull(message = "Amount is required")
        @DecimalMin(
                value = "0.01",
                message = "Amount must be greater than or equal to 0.01"
        )
        @Digits(
                integer = 17,
                fraction = 2,
                message = "Amount must have at most two decimal places"
        )
        BigDecimal amount

) {
}