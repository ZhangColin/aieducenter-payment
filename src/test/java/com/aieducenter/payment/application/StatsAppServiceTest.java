package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.response.GatewayHealthResponse;
import com.aieducenter.payment.application.dto.response.GatewayHealthResponse.InterfaceHealth;
import com.aieducenter.payment.application.dto.response.OperationsAuditResponse;
import com.aieducenter.payment.application.dto.response.OperationsAuditResponse.AuditorBreakdown;
import com.aieducenter.payment.application.dto.response.PaymentOverviewResponse;
import com.aieducenter.payment.application.dto.response.StatusDistributionResponse;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.enums.StatsGranularity;
import com.aieducenter.payment.domain.error.PaymentMessage;
import com.aieducenter.payment.infrastructure.query.StatsQueryRepository;
import com.aieducenter.payment.infrastructure.query.projection.AuditOperationCount;
import com.aieducenter.payment.infrastructure.query.projection.AuditorAuditCount;
import com.aieducenter.payment.infrastructure.query.projection.GatewayInterfaceRollup;
import com.aieducenter.payment.infrastructure.query.projection.GatewayReturnCodeCount;
import com.aieducenter.payment.infrastructure.query.projection.PaymentStatusCount;
import com.aieducenter.payment.infrastructure.query.projection.PaymentTrendBucket;
import com.aieducenter.payment.infrastructure.query.projection.RefundStatusCount;
import com.aieducenter.payment.infrastructure.query.projection.RefundTrendBucket;
import com.cartisan.core.exception.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * StatsAppService 测试（AppService Mockito 缝，issue #17）。
 *
 * <p>mock {@link StatsQueryRepository} 返回投影 record，断言 AppService 的**装配逻辑**：
 * 成功率/通过率计算（含除零保护）、净额、趋势桶序列补零、桶上限与区间校验。
 * 银行无关（CONTEXT.md 不变式 5）——SQL 不出现 ICBC 硬编码，由 jOOQ 实现保证。</p>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("统计应用服务测试")
class StatsAppServiceTest {

    @Mock
    private StatsQueryRepository statsQueryRepository;

    private StatsAppService service;

    @BeforeEach
    void setUp() {
        service = new StatsAppService(statsQueryRepository);
    }

    // ==================== overview ====================

    @Test
    @DisplayName("overview：装配支付/退款摘要（含成功率）+ 净额 + 趋势首尾桶补零")
    void given_statusCountsAndSparseTrend_when_overview_then_assembledWithRatesNetAndZeroFilledTrend() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 13, 0, 0);

        when(statsQueryRepository.countPaymentByStatus(from, to)).thenReturn(List.of(
            new PaymentStatusCount(PaymentStatus.PENDING.getCode(), 2L, 200L, 0L),
            new PaymentStatusCount(PaymentStatus.PAID.getCode(), 8L, 1000L, 980L),
            new PaymentStatusCount(PaymentStatus.FAILED.getCode(), 1L, 50L, 0L)
        ));
        when(statsQueryRepository.countRefundByStatus(from, to)).thenReturn(List.of(
            new RefundStatusCount(RefundStatus.PENDING.getCode(), 1L, 100L),
            new RefundStatusCount(RefundStatus.SUCCESS.getCode(), 3L, 300L)
        ));
        // 仅中间一天有数据，首尾桶应补零
        when(statsQueryRepository.paymentTrend(from, to, StatsGranularity.DAY)).thenReturn(List.of(
            new PaymentTrendBucket(LocalDateTime.of(2026, 8, 12, 0, 0), 5L, 600L, 4L, 480L)
        ));
        when(statsQueryRepository.refundTrend(from, to, StatsGranularity.DAY)).thenReturn(List.of(
            new RefundTrendBucket(LocalDateTime.of(2026, 8, 12, 0, 0), 2L, 200L, 1L, 100L)
        ));

        PaymentOverviewResponse result = service.overview(from, to, StatsGranularity.DAY);

        // 支付摘要：count=2+8+1=11, amount=1250, success=PAID 行(8,980), rate=8/11=0.7273
        assertThat(result.payment().count()).isEqualTo(11L);
        assertThat(result.payment().amount()).isEqualTo(1250L);
        assertThat(result.payment().successCount()).isEqualTo(8L);
        assertThat(result.payment().successAmount()).isEqualTo(980L);
        assertThat(result.payment().successRate()).isEqualByComparingTo(new BigDecimal("0.7273"));
        // 退款摘要：count=4, amount=400, success=SUCCESS 行(3,300), rate=3/4=0.7500
        assertThat(result.refund().count()).isEqualTo(4L);
        assertThat(result.refund().amount()).isEqualTo(400L);
        assertThat(result.refund().successCount()).isEqualTo(3L);
        assertThat(result.refund().successAmount()).isEqualTo(300L);
        assertThat(result.refund().successRate()).isEqualByComparingTo(new BigDecimal("0.7500"));
        // 净额 = 980 - 300 = 680
        assertThat(result.netAmount()).isEqualTo(680L);

        // 趋势：3 个日桶（8/11、8/12、8/13），首尾补零
        assertThat(result.trend()).hasSize(3);
        PaymentOverviewResponse.TrendBucket first = result.trend().get(0);
        assertThat(first.bucket()).isEqualTo(LocalDateTime.of(2026, 8, 11, 0, 0));
        assertThat(first.paymentCount()).isZero();
        assertThat(first.refundCount()).isZero();
        PaymentOverviewResponse.TrendBucket mid = result.trend().get(1);
        assertThat(mid.bucket()).isEqualTo(LocalDateTime.of(2026, 8, 12, 0, 0));
        assertThat(mid.paymentCount()).isEqualTo(5L);
        assertThat(mid.paymentAmount()).isEqualTo(600L);
        assertThat(mid.paidCount()).isEqualTo(4L);
        assertThat(mid.paidAmount()).isEqualTo(480L);
        assertThat(mid.refundCount()).isEqualTo(2L);
        assertThat(mid.refundAmount()).isEqualTo(200L);
        assertThat(mid.refundedCount()).isEqualTo(1L);
        assertThat(mid.refundedAmount()).isEqualTo(100L);
        PaymentOverviewResponse.TrendBucket last = result.trend().get(2);
        assertThat(last.bucket()).isEqualTo(LocalDateTime.of(2026, 8, 13, 0, 0));
        assertThat(last.paymentCount()).isZero();
    }

    @Test
    @DisplayName("overview：空窗口全零（count=0 时 successRate=0.0000，除零保护）")
    void given_emptyWindow_when_overview_then_allZerosAndZeroRates() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 11, 0, 0);

        when(statsQueryRepository.countPaymentByStatus(from, to)).thenReturn(List.of());
        when(statsQueryRepository.countRefundByStatus(from, to)).thenReturn(List.of());
        when(statsQueryRepository.paymentTrend(from, to, StatsGranularity.DAY)).thenReturn(List.of());
        when(statsQueryRepository.refundTrend(from, to, StatsGranularity.DAY)).thenReturn(List.of());

        PaymentOverviewResponse result = service.overview(from, to, StatsGranularity.DAY);

        assertThat(result.payment().count()).isZero();
        assertThat(result.payment().amount()).isZero();
        assertThat(result.payment().successCount()).isZero();
        assertThat(result.payment().successAmount()).isZero();
        assertThat(result.payment().successRate()).isEqualByComparingTo(new BigDecimal("0.0000"));
        assertThat(result.refund().successRate()).isEqualByComparingTo(new BigDecimal("0.0000"));
        assertThat(result.netAmount()).isZero();
        assertThat(result.trend()).hasSize(1);  // 8/11 一个桶，全零
        assertThat(result.trend().get(0).paymentCount()).isZero();
    }

    @Test
    @DisplayName("overview：granularity 为 null 时按 DAY 处理")
    void given_nullGranularity_when_overview_then_treatedAsDay() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 11, 0, 0);

        when(statsQueryRepository.countPaymentByStatus(from, to)).thenReturn(List.of());
        when(statsQueryRepository.countRefundByStatus(from, to)).thenReturn(List.of());
        when(statsQueryRepository.paymentTrend(from, to, StatsGranularity.DAY)).thenReturn(List.of());
        when(statsQueryRepository.refundTrend(from, to, StatsGranularity.DAY)).thenReturn(List.of());

        service.overview(from, to, null);

        // 验证传给仓储的是 DAY（变异友好）
        verify(statsQueryRepository).paymentTrend(from, to, StatsGranularity.DAY);
    }

    @Test
    @DisplayName("overview：from 晚于 to 抛 STATS_INVALID_RANGE")
    void given_fromAfterTo_when_overview_then_invalidRange() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 12, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 11, 0, 0);

        assertThatThrownBy(() -> service.overview(from, to, StatsGranularity.DAY))
            .isInstanceOf(ApplicationException.class)
            .hasMessageContaining(PaymentMessage.STATS_INVALID_RANGE.message());
    }

    @Test
    @DisplayName("overview：桶数超上限抛 STATS_RANGE_TOO_LARGE")
    void given_rangeExceedsBucketCap_when_overview_then_rangeTooLarge() {
        // 17 整天 × 24 = 408 小时 → 409 桶 > 400
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 28, 0, 0);

        assertThatThrownBy(() -> service.overview(from, to, StatsGranularity.HOUR))
            .isInstanceOf(ApplicationException.class)
            .hasMessageContaining(PaymentMessage.STATS_RANGE_TOO_LARGE.message());
    }

    // ==================== statusDistribution ====================

    @Test
    @DisplayName("statusDistribution：全局快照，缺失状态补零 + 退款 PENDING 积压")
    void given_partialStatusCounts_when_statusDistribution_then_allEnumValuesZeroFilledAndBacklog() {
        when(statsQueryRepository.countPaymentByStatus(null, null)).thenReturn(List.of(
            new PaymentStatusCount(PaymentStatus.PAID.getCode(), 8L, 1000L, 980L)
            // 其余 4 个支付状态缺失 → 补零
        ));
        when(statsQueryRepository.countRefundByStatus(null, null)).thenReturn(List.of(
            new RefundStatusCount(RefundStatus.PENDING.getCode(), 5L, 500L),
            new RefundStatusCount(RefundStatus.SUCCESS.getCode(), 3L, 300L)
        ));

        StatusDistributionResponse result = service.statusDistribution();

        assertThat(result.paymentStatuses()).hasSize(5);  // 5 个 PaymentStatus 全列
        StatusDistributionResponse.PaymentStatusBucket paid = result.paymentStatuses().stream()
            .filter(b -> PaymentStatus.PAID.getCode().equals(b.status())).findFirst().orElseThrow();
        assertThat(paid.count()).isEqualTo(8L);
        assertThat(paid.amount()).isEqualTo(1000L);
        assertThat(paid.statusName()).isEqualTo("已支付");
        StatusDistributionResponse.PaymentStatusBucket pending = result.paymentStatuses().stream()
            .filter(b -> PaymentStatus.PENDING.getCode().equals(b.status())).findFirst().orElseThrow();
        assertThat(pending.count()).isZero();  // 补零（变异点）
        assertThat(pending.amount()).isZero();

        assertThat(result.refundStatuses()).hasSize(6);  // 6 个 RefundStatus 全列
        StatusDistributionResponse.RefundStatusBucket successRefund = result.refundStatuses().stream()
            .filter(b -> RefundStatus.SUCCESS.getCode().equals(b.status())).findFirst().orElseThrow();
        assertThat(successRefund.count()).isEqualTo(3L);
        assertThat(successRefund.amount()).isEqualTo(300L);
        assertThat(result.refundBacklog().pendingCount()).isEqualTo(5L);
        assertThat(result.refundBacklog().pendingAmount()).isEqualTo(500L);
    }

    @Test
    @DisplayName("statusDistribution：空表全零，backlog 全零")
    void given_empty_when_statusDistribution_then_allZeroBacklog() {
        when(statsQueryRepository.countPaymentByStatus(null, null)).thenReturn(List.of());
        when(statsQueryRepository.countRefundByStatus(null, null)).thenReturn(List.of());

        StatusDistributionResponse result = service.statusDistribution();

        assertThat(result.paymentStatuses()).hasSize(5);
        assertThat(result.paymentStatuses()).allSatisfy(b -> assertThat(b.count()).isZero());
        assertThat(result.refundStatuses()).hasSize(6);
        assertThat(result.refundBacklog().pendingCount()).isZero();
        assertThat(result.refundBacklog().pendingAmount()).isZero();
    }

    // ==================== gatewayHealth ====================

    @Test
    @DisplayName("gatewayHealth：各接口成功率/平均耗时(HALF_UP) + returnCode 按接口归组")
    void given_rollupsAndReturnCodes_when_gatewayHealth_then_groupedWithRates() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 12, 0, 0);

        when(statsQueryRepository.gatewayInterfaceRollup(from, to)).thenReturn(List.of(
            new GatewayInterfaceRollup("ICBC", "qrcode/consumption", 10L, 8L, new BigDecimal("350.125"))
        ));
        when(statsQueryRepository.gatewayReturnCodeCounts(from, to)).thenReturn(List.of(
            new GatewayReturnCodeCount("qrcode/consumption", "0", 8L),
            new GatewayReturnCodeCount("qrcode/consumption", "9999", 2L),
            new GatewayReturnCodeCount("other-interface", "0", 1L)  // 无 rollup，应被忽略
        ));

        GatewayHealthResponse result = service.gatewayHealth(from, to);

        assertThat(result.interfaces()).hasSize(1);
        InterfaceHealth iface = result.interfaces().get(0);
        assertThat(iface.bankInterface()).isEqualTo("qrcode/consumption");
        assertThat(iface.totalCount()).isEqualTo(10L);
        assertThat(iface.successCount()).isEqualTo(8L);
        assertThat(iface.successRate()).isEqualByComparingTo(new BigDecimal("0.8000"));  // 8/10
        assertThat(iface.avgExecutionTimeMs()).isEqualByComparingTo(new BigDecimal("350.13"));  // HALF_UP
        assertThat(iface.returnCodes()).hasSize(2);  // "0" 与 "9999"，other-interface 被忽略
        assertThat(iface.returnCodes()).extracting(GatewayHealthResponse.ReturnCodeCount::returnCode)
            .containsExactlyInAnyOrder("0", "9999");
    }

    @Test
    @DisplayName("gatewayHealth：无数据返回空接口列表")
    void given_empty_when_gatewayHealth_then_emptyInterfaces() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 12, 0, 0);

        when(statsQueryRepository.gatewayInterfaceRollup(from, to)).thenReturn(List.of());
        when(statsQueryRepository.gatewayReturnCodeCounts(from, to)).thenReturn(List.of());

        GatewayHealthResponse result = service.gatewayHealth(from, to);

        assertThat(result.interfaces()).isEmpty();
    }

    // ==================== operationsAudit ====================

    @Test
    @DisplayName("operationsAudit：总览(笔数/通过率/平均时长) + 按审核人透视")
    void given_countsAndAuditors_when_operationsAudit_then_totalsRatesAndBreakdown() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 12, 0, 0);

        when(statsQueryRepository.auditOperationCounts(from, to)).thenReturn(List.of(
            new AuditOperationCount(OperationType.AUDIT_APPROVE.getCode(), 6L),
            new AuditOperationCount(OperationType.AUDIT_REJECT.getCode(), 1L)
        ));
        when(statsQueryRepository.auditorAuditCounts(from, to)).thenReturn(List.of(
            new AuditorAuditCount(1L, "Alice", OperationType.AUDIT_APPROVE.getCode(), 4L),
            new AuditorAuditCount(1L, "Alice", OperationType.AUDIT_REJECT.getCode(), 1L),
            new AuditorAuditCount(2L, "Bob", OperationType.AUDIT_APPROVE.getCode(), 2L)
        ));
        when(statsQueryRepository.avgAuditDuration(from, to)).thenReturn(new BigDecimal("12.555"));

        OperationsAuditResponse result = service.operationsAudit(from, to);

        assertThat(result.totalAudits()).isEqualTo(7L);
        assertThat(result.approvedCount()).isEqualTo(6L);
        assertThat(result.rejectedCount()).isEqualTo(1L);
        assertThat(result.approvalRate()).isEqualByComparingTo(new BigDecimal("0.8571"));  // 6/7
        assertThat(result.avgAuditDurationMinutes()).isEqualByComparingTo(new BigDecimal("12.56"));  // HALF_UP

        assertThat(result.byAuditor()).hasSize(2);
        AuditorBreakdown alice = result.byAuditor().get(0);
        assertThat(alice.auditorName()).isEqualTo("Alice");
        assertThat(alice.count()).isEqualTo(5L);          // 4 + 1
        assertThat(alice.approvedCount()).isEqualTo(4L);
        assertThat(alice.rejectedCount()).isEqualTo(1L);
        assertThat(alice.approvalRate()).isEqualByComparingTo(new BigDecimal("0.8000"));  // 4/5
        AuditorBreakdown bob = result.byAuditor().get(1);
        assertThat(bob.approvedCount()).isEqualTo(2L);
        assertThat(bob.rejectedCount()).isZero();
        assertThat(bob.approvalRate()).isEqualByComparingTo(new BigDecimal("1.0000"));  // 2/2
    }

    @Test
    @DisplayName("operationsAudit：无数据全零（avgDuration COALESCE 为 0，byAuditor 空）")
    void given_empty_when_operationsAudit_then_allZeros() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 12, 0, 0);

        when(statsQueryRepository.auditOperationCounts(from, to)).thenReturn(List.of());
        when(statsQueryRepository.auditorAuditCounts(from, to)).thenReturn(List.of());
        when(statsQueryRepository.avgAuditDuration(from, to)).thenReturn(BigDecimal.ZERO);

        OperationsAuditResponse result = service.operationsAudit(from, to);

        assertThat(result.totalAudits()).isZero();
        assertThat(result.approvedCount()).isZero();
        assertThat(result.rejectedCount()).isZero();
        assertThat(result.approvalRate()).isEqualByComparingTo(new BigDecimal("0.0000"));  // 除零保护
        assertThat(result.avgAuditDurationMinutes()).isEqualByComparingTo(new BigDecimal("0.00"));
        assertThat(result.byAuditor()).isEmpty();
    }

    @Test
    @DisplayName("operationsAudit：仓储返回 null 平均时长时视作 0（履行接口契约，防 NPE）")
    void given_nullAvgDuration_when_operationsAudit_then_treatedAsZero() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 12, 0, 0);

        when(statsQueryRepository.auditOperationCounts(from, to)).thenReturn(List.of());
        when(statsQueryRepository.auditorAuditCounts(from, to)).thenReturn(List.of());
        when(statsQueryRepository.avgAuditDuration(from, to)).thenReturn(null);

        OperationsAuditResponse result = service.operationsAudit(from, to);

        assertThat(result.avgAuditDurationMinutes()).isEqualByComparingTo(new BigDecimal("0.00"));
    }

    // ==================== 边界与跨方法校验 ====================

    @Test
    @DisplayName("overview：DAY 粒度把非整点 from 截断到当天 00:00 作为首桶（变异点：cursor 初始化）")
    void given_nonMidnightFrom_when_overviewDay_then_firstBucketTruncatedToDayStart() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 14, 30);
        LocalDateTime to = LocalDateTime.of(2026, 8, 12, 9, 15);

        when(statsQueryRepository.countPaymentByStatus(from, to)).thenReturn(List.of());
        when(statsQueryRepository.countRefundByStatus(from, to)).thenReturn(List.of());
        when(statsQueryRepository.paymentTrend(from, to, StatsGranularity.DAY)).thenReturn(List.of());
        when(statsQueryRepository.refundTrend(from, to, StatsGranularity.DAY)).thenReturn(List.of());

        PaymentOverviewResponse result = service.overview(from, to, StatsGranularity.DAY);

        // from 14:30 截断到 8/11 00:00；to 8/12 09:15 落在 8/12 日桶
        assertThat(result.trend()).hasSize(2);
        assertThat(result.trend().get(0).bucket()).isEqualTo(LocalDateTime.of(2026, 8, 11, 0, 0));
        assertThat(result.trend().get(1).bucket()).isEqualTo(LocalDateTime.of(2026, 8, 12, 0, 0));
    }

    @Test
    @DisplayName("overview：桶数恰为上限 400 时不抛异常（变异点：边界 >）")
    void given_rangeAtBucketCap_when_overview_then_noThrow() {
        LocalDateTime from = LocalDateTime.of(2025, 1, 1, 0, 0);
        LocalDateTime to = from.plusDays(399);  // 含首尾 400 个日桶

        when(statsQueryRepository.countPaymentByStatus(from, to)).thenReturn(List.of());
        when(statsQueryRepository.countRefundByStatus(from, to)).thenReturn(List.of());
        when(statsQueryRepository.paymentTrend(from, to, StatsGranularity.DAY)).thenReturn(List.of());
        when(statsQueryRepository.refundTrend(from, to, StatsGranularity.DAY)).thenReturn(List.of());

        PaymentOverviewResponse result = service.overview(from, to, StatsGranularity.DAY);

        assertThat(result.trend()).hasSize(400);
    }

    @Test
    @DisplayName("gatewayHealth：from 晚于 to 抛 STATS_INVALID_RANGE")
    void given_fromAfterTo_when_gatewayHealth_then_invalidRange() {
        assertThatThrownBy(() -> service.gatewayHealth(
                LocalDateTime.of(2026, 8, 12, 0, 0), LocalDateTime.of(2026, 8, 11, 0, 0)))
            .isInstanceOf(ApplicationException.class)
            .hasMessageContaining(PaymentMessage.STATS_INVALID_RANGE.message());
    }

    @Test
    @DisplayName("operationsAudit：from 晚于 to 抛 STATS_INVALID_RANGE")
    void given_fromAfterTo_when_operationsAudit_then_invalidRange() {
        assertThatThrownBy(() -> service.operationsAudit(
                LocalDateTime.of(2026, 8, 12, 0, 0), LocalDateTime.of(2026, 8, 11, 0, 0)))
            .isInstanceOf(ApplicationException.class)
            .hasMessageContaining(PaymentMessage.STATS_INVALID_RANGE.message());
    }
}
