package com.aieducenter.payment.hsb.application.dto.command;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateHsbRefundCommand(
    @NotBlank String paymentOrderNo,
    @NotBlank String businessMainOrderNo,
    String refundType,
    @NotNull @Positive Long refundAmount,
    String reason,
    String notifyUrl,
    String attach,
    @Valid List<HsbRefundSubOrderCommand> subOrders
) {
    @Builder
    public record HsbRefundSubOrderCommand(
        @NotBlank String businessSubOrderNo,
        @NotNull @Positive Long refundAmount
    ) {}
}
