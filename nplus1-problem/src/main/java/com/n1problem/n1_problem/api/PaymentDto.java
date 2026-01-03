package com.n1problem.n1_problem.api;

public record PaymentDto(
        Long id,
        Double amount,
        Long clientId
) {
}
