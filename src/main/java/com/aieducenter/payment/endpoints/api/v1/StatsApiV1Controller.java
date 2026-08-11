package com.aieducenter.payment.endpoints.api.v1;

import com.aieducenter.payment.application.StatsAppService;
import com.aieducenter.payment.application.dto.response.AnomaliesResponse;
import com.aieducenter.payment.application.dto.response.ByBusinessSystemResponse;
import com.aieducenter.payment.application.dto.response.ByChannelResponse;
import com.aieducenter.payment.application.dto.response.GatewayHealthResponse;
import com.aieducenter.payment.application.dto.response.OperationsActivityResponse;
import com.aieducenter.payment.application.dto.response.OperationsAuditResponse;
import com.aieducenter.payment.application.dto.response.PaymentOverviewResponse;
import com.aieducenter.payment.application.dto.response.StatusDistributionResponse;
import com.aieducenter.payment.domain.enums.StatsGranularity;
import com.cartisan.openapi.annotation.RequireSignature;
import com.cartisan.web.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 统计一档接口（issue #17）。薄委托到 {@link StatsAppService}；全部 {@code @RequireSignature}、银行无关。
 */
@RestController
@RequestMapping("/api/v1/stats")
@RequiredArgsConstructor
@Validated
@Tag(name = "Stats API v1", description = "统计一档接口 v1")
public class StatsApiV1Controller {

    private final StatsAppService statsAppService;

    @GetMapping("/payments/overview")
    @RequireSignature
    @Operation(summary = "支付交易概览：支付/退款笔数·金额·成功率·净额，按日/小时分桶趋势")
    public ApiResponse<PaymentOverviewResponse> overview(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @RequestParam(required = false) StatsGranularity granularity
    ) {
        return ApiResponse.ok(statsAppService.overview(from, to, granularity));
    }

    @GetMapping("/orders/status-distribution")
    @RequireSignature
    @Operation(summary = "订单状态在途分布：各状态笔数·金额，退款待审核积压")
    public ApiResponse<StatusDistributionResponse> statusDistribution() {
        return ApiResponse.ok(statsAppService.statusDistribution());
    }

    @GetMapping("/gateway/health")
    @RequireSignature
    @Operation(summary = "银行网关健康度：各接口调用次数·成功率·平均耗时·returnCode 分布")
    public ApiResponse<GatewayHealthResponse> gatewayHealth(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        return ApiResponse.ok(statsAppService.gatewayHealth(from, to));
    }

    @GetMapping("/operations/audit")
    @RequireSignature
    @Operation(summary = "退款审核工作情况：审核笔数·通过率·平均审核时长·按审核人聚合")
    public ApiResponse<OperationsAuditResponse> operationsAudit(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        return ApiResponse.ok(statsAppService.operationsAudit(from, to));
    }

    @GetMapping("/by-business-system")
    @RequireSignature
    @Operation(summary = "按业务系统维度：各业务系统支付/退款笔数·金额·成功率·退款率")
    public ApiResponse<ByBusinessSystemResponse> byBusinessSystem(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        return ApiResponse.ok(statsAppService.byBusinessSystem(from, to));
    }

    @GetMapping("/by-channel")
    @RequireSignature
    @Operation(summary = "按渠道维度：各支付方式/接入类型笔数·金额·成功率")
    public ApiResponse<ByChannelResponse> byChannel(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        return ApiResponse.ok(statsAppService.byChannel(from, to));
    }

    @GetMapping("/anomalies")
    @RequireSignature
    @Operation(summary = "异常监控：长时 PENDING/REFUNDING 单 + 近期查询/回调失败计数（阈值可配）")
    public ApiResponse<AnomaliesResponse> anomalies() {
        return ApiResponse.ok(statsAppService.anomalies());
    }

    @GetMapping("/operations/activity")
    @RequireSignature
    @Operation(summary = "操作活跃度：各操作员操作类型/笔数 + 通知重发次数及来源业务系统")
    public ApiResponse<OperationsActivityResponse> operationsActivity(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        return ApiResponse.ok(statsAppService.operationsActivity(from, to));
    }
}
