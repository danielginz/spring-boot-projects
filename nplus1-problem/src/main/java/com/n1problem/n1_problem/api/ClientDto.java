package com.n1problem.n1_problem.api;

import java.util.List;

public record ClientDto (
    Long id,
    String name,
    List<PaymentDto> payments
) {
}
