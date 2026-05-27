package com.aieducenter.payment.hsb.endpoints.api.v1;

import com.aieducenter.payment.hsb.application.HsbRefundAppService;
import com.aieducenter.payment.hsb.application.dto.command.CreateHsbRefundCommand;
import com.aieducenter.payment.hsb.application.dto.response.HsbRefundOrderResponse;
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
@RequestMapping("/api/v1/hsb/refunds")
@RequiredArgsConstructor
@Validated
@Tag(name = "HSB Refund API v1", description = "惠市宝退款接口")
public class HsbRefundController {

    private final HsbRefundAppService refundAppService;

    @PostMapping
    @RequireSignature
    @Operation(summary = "创建惠市宝退款订单")
    public ApiResponse<HsbRefundOrderResponse> createRefund(
            @Valid @RequestBody CreateHsbRefundCommand command
    ) {
        String businessSystemName = RequestContext.getCallerAppName();
        HsbRefundOrderResponse response = refundAppService.createRefund(command, businessSystemName);
        return ApiResponse.ok(response);
    }

    @GetMapping("/{refundOrderNo}")
    @RequireSignature
    @Operation(summary = "查询惠市宝退款订单")
    public ApiResponse<HsbRefundOrderResponse> getRefund(
            @PathVariable String refundOrderNo
    ) {
        HsbRefundOrderResponse response = refundAppService.getRefund(refundOrderNo);
        return ApiResponse.ok(response);
    }

    @PostMapping("/{refundOrderNo}/query")
    @RequireSignature
    @Operation(summary = "主动查询惠市宝退款状态")
    public ApiResponse<HsbRefundOrderResponse> queryRefundStatus(
            @PathVariable String refundOrderNo
    ) {
        HsbRefundOrderResponse response = refundAppService.queryRefundStatus(refundOrderNo);
        return ApiResponse.ok(response);
    }
}
