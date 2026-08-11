package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.payment.application.PaymentAppService;
import com.aieducenter.payment.application.PaymentOrderQueryAppService;
import com.aieducenter.payment.application.PrepayAppService;
import com.aieducenter.payment.application.dto.command.CreatePaymentCommand;
import com.aieducenter.payment.application.dto.command.CreatePrepayCommand;
import com.aieducenter.payment.application.dto.query.PaymentOrderQuery;
import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.application.dto.response.PrepayOrderResponse;
import com.cartisan.core.context.RequestContext;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.response.ApiResponse;
import com.cartisan.web.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.cartisan.web.util.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Validated
@Tag(name = "Payment API v1", description = "支付接口 v1")
public class PaymentApiV1Controller {

    private final PaymentAppService paymentAppService;
    private final PaymentOrderQueryAppService paymentOrderQueryAppService;

    @PostMapping
    @RequireSignature
    @Operation(summary = "创建支付订单")
    public ApiResponse<PaymentOrderResponse> createPayment(
            @Valid @RequestBody CreatePaymentCommand command,
            HttpServletRequest request
    ) {
        String businessSystemName = RequestContext.getCallerAppName();
        String clientIp = IpUtil.getClientIp(request);
        PaymentOrderResponse response = paymentAppService.createPayment(command, businessSystemName, clientIp);
        return ApiResponse.ok(response);
    }

    private final PrepayAppService prepayAppService;

    @PostMapping("/prepay")
    @RequireSignature
    @Operation(summary = "创建预支付订单")
    public ApiResponse<PrepayOrderResponse> createPrepay(
            @Valid @RequestBody CreatePrepayCommand command,
            HttpServletRequest request
    ) {
        String businessSystemName = RequestContext.getCallerAppName();
        String clientIp = IpUtil.getClientIp(request);
        PrepayOrderResponse response = prepayAppService.createPrepay(command, businessSystemName, clientIp);
        return ApiResponse.ok(response);
    }

    @GetMapping
    @RequireSignature
    @Operation(summary = "分页查询支付订单")
    public ApiResponse<PageResponse<PaymentOrderResponse>> list(
            PaymentOrderQuery query,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        return ApiResponse.ok(paymentOrderQueryAppService.list(query, pageable));
    }

    @GetMapping("/{paymentOrderNo}")
    @RequireSignature
    @Operation(summary = "查询支付订单")
    public ApiResponse<PaymentOrderResponse> getPayment(
            @PathVariable String paymentOrderNo
    ) {
        PaymentOrderResponse response = paymentAppService.getPayment(paymentOrderNo);
        return ApiResponse.ok(response);
    }

    @PostMapping("/{paymentOrderNo}/query")
    @RequireSignature
    @Operation(summary = "主动查询支付状态")
    public ApiResponse<PaymentOrderResponse> queryPaymentStatus(
            @PathVariable String paymentOrderNo
    ) {
        PaymentOrderResponse response = paymentAppService.queryPaymentStatus(paymentOrderNo);
        return ApiResponse.ok(response);
    }

    @PostMapping("/{paymentOrderNo}/cancel")
    @RequireSignature
    @Operation(summary = "取消支付订单")
    public ApiResponse<PaymentOrderResponse> cancelPayment(
            @PathVariable String paymentOrderNo
    ) {
        PaymentOrderResponse response = paymentAppService.cancelPayment(paymentOrderNo);
        return ApiResponse.ok(response);
    }

}
