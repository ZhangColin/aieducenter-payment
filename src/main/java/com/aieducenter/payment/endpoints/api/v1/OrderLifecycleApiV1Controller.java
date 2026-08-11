package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.payment.application.OrderLifecycleAppService;
import com.aieducenter.payment.application.dto.response.OrderLifecycleResponse;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
@Tag(name = "Order Lifecycle API v1", description = "订单生命周期接口 v1")
public class OrderLifecycleApiV1Controller {

    private final OrderLifecycleAppService orderLifecycleAppService;

    @GetMapping("/{orderNo}/lifecycle")
    @RequireSignature
    @Operation(summary = "查询订单生命周期事件序列（PaymentLog + OperationLog 按时间合并）")
    public ApiResponse<List<OrderLifecycleResponse>> getLifecycle(
            @PathVariable String orderNo
    ) {
        return ApiResponse.ok(orderLifecycleAppService.getLifecycle(orderNo));
    }
}
