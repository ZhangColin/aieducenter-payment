package com.aieducenter.payment.hsb.application.dto.command;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record CreateHsbPaymentCommand(
    @NotBlank String businessMainOrderNo,
    String businessName,
    @NotBlank String paymentMethod,
    @NotBlank String orderType,
    String currency,
    @NotNull @Positive Long totalAmount,
    @NotNull @Positive Long txnTotalAmount,
    String feeBearerId,
    Long expiredSeconds,
    String notifyUrl,
    String attach,
    String pageReturnUrl,
    LocalDate confirmReceiptDate,
    @NotEmpty @Valid List<HsbSubOrderCommand> subOrders
) {
    @Builder
    public record HsbSubOrderCommand(
        @NotBlank String businessSubOrderNo,
        @NotBlank String mktMrchId,
        @NotNull @Positive Long orderAmount,
        @NotNull @Positive Long txnAmount
    ) {}
}
