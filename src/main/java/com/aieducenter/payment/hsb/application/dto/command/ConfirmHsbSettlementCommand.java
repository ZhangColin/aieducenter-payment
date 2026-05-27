package com.aieducenter.payment.hsb.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
public record ConfirmHsbSettlementCommand(
    @NotBlank String paymentOrderNo,
    @NotEmpty List<String> businessSubOrderNos
) {}
