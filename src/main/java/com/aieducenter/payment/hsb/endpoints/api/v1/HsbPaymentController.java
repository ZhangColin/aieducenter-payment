package com.aieducenter.payment.hsb.endpoints.api.v1;

import com.aieducenter.payment.hsb.application.HsbPaymentAppService;
import com.aieducenter.payment.hsb.application.dto.command.CreateHsbPaymentCommand;
import com.aieducenter.payment.hsb.application.dto.response.HsbPaymentOrderResponse;
import com.cartisan.core.context.RequestContext;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hsb/payments")
@RequiredArgsConstructor
@Validated
@Tag(name = "HSB Payment API v1", description = "惠市宝支付接口")
public class HsbPaymentController {

    private final HsbPaymentAppService paymentAppService;

    @PostMapping
    @RequireSignature
    @Operation(summary = "创建惠市宝支付订单")
    public ApiResponse<HsbPaymentOrderResponse> createPayment(
            @Valid @RequestBody CreateHsbPaymentCommand command
    ) {
        String businessSystemName = RequestContext.getCallerAppName();
        HsbPaymentOrderResponse response = paymentAppService.createPayment(command, businessSystemName);
        return ApiResponse.ok(response);
    }

    @GetMapping("/{paymentOrderNo}")
    @RequireSignature
    @Operation(summary = "查询惠市宝支付订单")
    public ApiResponse<HsbPaymentOrderResponse> getPayment(
            @PathVariable String paymentOrderNo
    ) {
        HsbPaymentOrderResponse response = paymentAppService.getPayment(paymentOrderNo);
        return ApiResponse.ok(response);
    }

    @PostMapping("/{paymentOrderNo}/query")
    @RequireSignature
    @Operation(summary = "主动查询惠市宝支付状态")
    public ApiResponse<HsbPaymentOrderResponse> queryPaymentStatus(
            @PathVariable String paymentOrderNo
    ) {
        HsbPaymentOrderResponse response = paymentAppService.queryPaymentStatus(paymentOrderNo);
        return ApiResponse.ok(response);
    }
}
