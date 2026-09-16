package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.payment.application.NotificationResendAppService;
import com.aieducenter.payment.application.RefundAppService;
import com.aieducenter.payment.application.RefundOrderQueryAppService;
import com.aieducenter.payment.application.dto.command.AuditRefundCommand;
import com.aieducenter.payment.application.dto.command.CreateRefundCommand;
import com.aieducenter.payment.application.dto.command.ResendNotificationCommand;
import com.aieducenter.payment.application.dto.query.RefundOrderQuery;
import com.aieducenter.payment.application.dto.response.RefundOrderResponse;
import com.cartisan.core.context.RequestContext;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.request.Pagination;
import com.cartisan.web.response.ApiResponse;
import com.cartisan.web.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/refunds")
@RequiredArgsConstructor
@Validated
@Tag(name = "Refund API v1", description = "退款接口 v1")
public class RefundApiV1Controller {

    private final RefundAppService refundAppService;
    private final RefundOrderQueryAppService refundOrderQueryAppService;
    private final NotificationResendAppService notificationResendAppService;

    @PostMapping
    @RequireSignature
    @Operation(summary = "创建退款订单")
    public ApiResponse<RefundOrderResponse> createRefund(
            @Valid @RequestBody CreateRefundCommand command,
            HttpServletRequest request
    ) {
        String businessSystemName = RequestContext.getCallerAppName();
        RefundOrderResponse response = refundAppService.createRefund(command, businessSystemName);
        return ApiResponse.ok(response);
    }

    @PostMapping("/{refundOrderNo}/audit")
    @RequireSignature
    @Operation(summary = "审核退款")
    public ApiResponse<RefundOrderResponse> auditRefund(
            @PathVariable String refundOrderNo,
            @Valid @RequestBody AuditRefundCommand command
    ) {
        RefundOrderResponse response = refundAppService.auditRefund(refundOrderNo, command);
        return ApiResponse.ok(response);
    }

    @PostMapping("/{refundOrderNo}/notifications/resend")
    @RequireSignature
    @Operation(summary = "重发退款结果通知")
    public ApiResponse<Void> resendRefundNotification(
            @PathVariable String refundOrderNo,
            @Valid @RequestBody ResendNotificationCommand command
    ) {
        notificationResendAppService.resendRefundNotification(refundOrderNo, command);
        return ApiResponse.ok();
    }

    @GetMapping
    @RequireSignature
    @Operation(summary = "分页查询退款订单",
        description = "分页全链 1-based：page 从 1 起（缺省 1），size 缺省 20、clamp 至 [1,100]；响应回显 page 同为 1-based")
    public ApiResponse<PageResponse<RefundOrderResponse>> list(
            RefundOrderQuery query,
            Pagination pagination
    ) {
        return ApiResponse.ok(refundOrderQueryAppService.list(query, pagination));
    }

    @GetMapping("/{refundOrderNo}")
    @RequireSignature
    @Operation(summary = "查询退款订单")
    public ApiResponse<RefundOrderResponse> getRefund(
            @PathVariable String refundOrderNo
    ) {
        RefundOrderResponse response = refundAppService.queryRefund(refundOrderNo);
        return ApiResponse.ok(response);
    }
}
