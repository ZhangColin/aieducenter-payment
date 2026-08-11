package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.payment.application.PaymentLogQueryAppService;
import com.aieducenter.payment.application.dto.query.PaymentLogQuery;
import com.aieducenter.payment.application.dto.response.PaymentLogResponse;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.response.ApiResponse;
import com.cartisan.web.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment-logs")
@RequiredArgsConstructor
@Validated
@Tag(name = "Payment Log API v1", description = "支付网关日志接口 v1")
public class PaymentLogApiV1Controller {

    private final PaymentLogQueryAppService paymentLogQueryAppService;

    @GetMapping
    @RequireSignature
    @Operation(summary = "分页查询支付网关日志")
    public ApiResponse<PageResponse<PaymentLogResponse>> list(
            PaymentLogQuery query,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return ApiResponse.ok(paymentLogQueryAppService.list(query, pageable));
    }
}
