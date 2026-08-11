package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.response.AnomaliesResponse;
import com.aieducenter.payment.application.dto.response.ByBusinessSystemResponse;
import com.aieducenter.payment.application.dto.response.ByChannelResponse;
import com.aieducenter.payment.application.dto.response.GatewayHealthResponse;
import com.aieducenter.payment.application.dto.response.OperationsActivityResponse;
import com.aieducenter.payment.application.dto.response.GatewayHealthResponse.InterfaceHealth;
import com.aieducenter.payment.application.dto.response.OperationsAuditResponse;
import com.aieducenter.payment.application.dto.response.OperationsAuditResponse.AuditorBreakdown;
import com.aieducenter.payment.application.dto.response.PaymentOverviewResponse;
import com.aieducenter.payment.application.dto.response.StatusDistributionResponse;
import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.enums.PayMode;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.enums.StatsGranularity;
import com.aieducenter.payment.domain.error.PaymentMessage;
import com.aieducenter.payment.infrastructure.query.StatsQueryRepository;
import com.aieducenter.payment.infrastructure.query.projection.AuditOperationCount;
import com.aieducenter.payment.infrastructure.query.projection.AuditorAuditCount;
import com.aieducenter.payment.infrastructure.query.projection.BusinessSystemPaymentRollup;
import com.aieducenter.payment.infrastructure.query.projection.BusinessSystemRefundRollup;
import com.aieducenter.payment.infrastructure.query.projection.ChannelPaymentRollup;
import com.aieducenter.payment.infrastructure.query.projection.FailureCountByType;
import com.aieducenter.payment.infrastructure.query.projection.GatewayInterfaceRollup;
import com.aieducenter.payment.infrastructure.query.projection.GatewayReturnCodeCount;
import com.aieducenter.payment.infrastructure.query.projection.NotifyResendBySystem;
import com.aieducenter.payment.infrastructure.query.projection.OperatorOperationCount;
import com.aieducenter.payment.infrastructure.query.projection.PaymentStatusCount;
import com.aieducenter.payment.infrastructure.query.projection.PaymentTrendBucket;
import com.aieducenter.payment.infrastructure.query.projection.RefundStatusCount;
import com.aieducenter.payment.infrastructure.query.projection.RefundTrendBucket;
import com.aieducenter.payment.infrastructure.query.projection.StuckOrderCount;
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
        // 阈值取配置默认值（24/48/1 小时），与 application.yml 默认一致
        service = new StatsAppService(statsQueryRepository, 24, 48, 1);
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

    // ==================== byBusinessSystem ====================

    @Test
    @DisplayName("byBusinessSystem：双源并集 + 缺失侧补零 + refundRate（refundedCount/paidCount）")
    void given_paymentAndRefundRollups_when_byBusinessSystem_then_unionWithZeroFillAndRefundRate() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 12, 0, 0);

        when(statsQueryRepository.businessSystemPaymentRollup(from, to)).thenReturn(List.of(
            new BusinessSystemPaymentRollup("edu", 10L, 1000L, 8L, 800L),
            new BusinessSystemPaymentRollup("shop", 4L, 400L, 4L, 400L)
        ));
        when(statsQueryRepository.businessSystemRefundRollup(from, to)).thenReturn(List.of(
            new BusinessSystemRefundRollup("edu", 2L, 200L, 1L, 100L),
            new BusinessSystemRefundRollup("vip", 3L, 300L, 3L, 300L)  // refund-only → payment 侧补零
        ));

        ByBusinessSystemResponse result = service.byBusinessSystem(from, to);

        // 并集 3 个：edu、shop（payment 顺序优先）、vip（refund-only 追加）
        assertThat(result.businessSystems()).hasSize(3);
        assertThat(result.businessSystems()).extracting(ByBusinessSystemResponse.BusinessSystemBreakdown::businessSystemName)
            .containsExactly("edu", "shop", "vip");

        // edu: payment(10,1000,8,800,0.8), refund(2,200,1,100,0.5), refundRate=1/8=0.125
        ByBusinessSystemResponse.BusinessSystemBreakdown edu = result.businessSystems().get(0);
        assertThat(edu.payment().count()).isEqualTo(10L);
        assertThat(edu.payment().amount()).isEqualTo(1000L);
        assertThat(edu.payment().successCount()).isEqualTo(8L);
        assertThat(edu.payment().successAmount()).isEqualTo(800L);
        assertThat(edu.payment().successRate()).isEqualByComparingTo(new BigDecimal("0.8000"));
        assertThat(edu.refund().count()).isEqualTo(2L);
        assertThat(edu.refund().amount()).isEqualTo(200L);
        assertThat(edu.refund().successCount()).isEqualTo(1L);
        assertThat(edu.refund().successAmount()).isEqualTo(100L);
        assertThat(edu.refund().successRate()).isEqualByComparingTo(new BigDecimal("0.5000"));
        assertThat(edu.refundRate()).isEqualByComparingTo(new BigDecimal("0.1250"));  // 1/8

        // shop: refund 侧缺失 → 补零；refundRate = 0/4 = 0
        ByBusinessSystemResponse.BusinessSystemBreakdown shop = result.businessSystems().get(1);
        assertThat(shop.refund().count()).isZero();
        assertThat(shop.refund().successCount()).isZero();
        assertThat(shop.refundRate()).isEqualByComparingTo(new BigDecimal("0.0000"));

        // vip: payment 侧缺失 → 补零；refundRate = 3/0 → 除零保护 0
        ByBusinessSystemResponse.BusinessSystemBreakdown vip = result.businessSystems().get(2);
        assertThat(vip.payment().count()).isZero();
        assertThat(vip.payment().successCount()).isZero();
        assertThat(vip.refund().successCount()).isEqualTo(3L);
        assertThat(vip.refundRate()).isEqualByComparingTo(new BigDecimal("0.0000"));  // 除零保护
    }

    @Test
    @DisplayName("byBusinessSystem：空表返回空列表")
    void given_empty_when_byBusinessSystem_then_emptyList() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 12, 0, 0);

        when(statsQueryRepository.businessSystemPaymentRollup(from, to)).thenReturn(List.of());
        when(statsQueryRepository.businessSystemRefundRollup(from, to)).thenReturn(List.of());

        ByBusinessSystemResponse result = service.byBusinessSystem(from, to);

        assertThat(result.businessSystems()).isEmpty();
    }

    @Test
    @DisplayName("byBusinessSystem：from 晚于 to 抛 STATS_INVALID_RANGE")
    void given_fromAfterTo_when_byBusinessSystem_then_invalidRange() {
        assertThatThrownBy(() -> service.byBusinessSystem(
                LocalDateTime.of(2026, 8, 12, 0, 0), LocalDateTime.of(2026, 8, 11, 0, 0)))
            .isInstanceOf(ApplicationException.class)
            .hasMessageContaining(PaymentMessage.STATS_INVALID_RANGE.message());
    }

    // ==================== byChannel ====================

    @Test
    @DisplayName("byChannel：pay_mode/access_type 各自聚合 + 枚举补零 + code→name 映射 + 成功率")
    void given_payModeAndAccessTypeRollups_when_byChannel_then_zeroFilledWithNamesAndRates() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 12, 0, 0);

        // pay_mode: 仅 WECHAT(9) 与 ALIPAY(10) 有数据，UNIONPAY(13) 缺失补零
        when(statsQueryRepository.payModeRollup(from, to)).thenReturn(List.of(
            new ChannelPaymentRollup(PayMode.WECHAT.getCode(), 10L, 1000L, 8L, 800L),
            new ChannelPaymentRollup(PayMode.ALIPAY.getCode(), 5L, 500L, 5L, 500L)
        ));
        // access_type: 仅 H5(4) 有数据，其余 4 个缺失补零
        when(statsQueryRepository.accessTypeRollup(from, to)).thenReturn(List.of(
            new ChannelPaymentRollup(AccessType.H5.getCode(), 4L, 400L, 2L, 200L)
        ));

        ByChannelResponse result = service.byChannel(from, to);

        // byPayMode: 3 个枚举值（WECHAT/ALIPAY/UNIONPAY），按枚举顺序
        assertThat(result.byPayMode()).hasSize(3);
        assertThat(result.byPayMode()).extracting(ByChannelResponse.ChannelBreakdown::channelName)
            .containsExactly("微信", "支付宝", "云闪付");
        ByChannelResponse.ChannelBreakdown wechat = result.byPayMode().get(0);
        assertThat(wechat.channelCode()).isEqualTo(PayMode.WECHAT.getCode());
        assertThat(wechat.count()).isEqualTo(10L);
        assertThat(wechat.amount()).isEqualTo(1000L);
        assertThat(wechat.successCount()).isEqualTo(8L);
        assertThat(wechat.successAmount()).isEqualTo(800L);
        assertThat(wechat.successRate()).isEqualByComparingTo(new BigDecimal("0.8000"));  // 8/10
        ByChannelResponse.ChannelBreakdown unionpay = result.byPayMode().get(2);
        assertThat(unionpay.count()).isZero();  // 补零
        assertThat(unionpay.channelName()).isEqualTo("云闪付");
        assertThat(unionpay.successRate()).isEqualByComparingTo(new BigDecimal("0.0000"));  // 除零保护

        // byAccessType: 5 个枚举值，按枚举顺序，仅 H5 有数据
        assertThat(result.byAccessType()).hasSize(5);
        assertThat(result.byAccessType()).extracting(ByChannelResponse.ChannelBreakdown::channelName)
            .containsExactly("H5", "APP", "微信公众号", "支付宝生活号", "小程序");
        ByChannelResponse.ChannelBreakdown h5 = result.byAccessType().get(0);
        assertThat(h5.count()).isEqualTo(4L);
        assertThat(h5.successRate()).isEqualByComparingTo(new BigDecimal("0.5000"));  // 2/4
    }

    @Test
    @DisplayName("byChannel：空表返回全枚举补零（含名称）")
    void given_empty_when_byChannel_then_allEnumsZeroFilled() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 12, 0, 0);

        when(statsQueryRepository.payModeRollup(from, to)).thenReturn(List.of());
        when(statsQueryRepository.accessTypeRollup(from, to)).thenReturn(List.of());

        ByChannelResponse result = service.byChannel(from, to);

        assertThat(result.byPayMode()).hasSize(3);
        assertThat(result.byPayMode()).allSatisfy(b -> assertThat(b.count()).isZero());
        assertThat(result.byAccessType()).hasSize(5);
        assertThat(result.byAccessType()).allSatisfy(b -> assertThat(b.count()).isZero());
    }

    @Test
    @DisplayName("byChannel：from 晚于 to 抛 STATS_INVALID_RANGE")
    void given_fromAfterTo_when_byChannel_then_invalidRange() {
        assertThatThrownBy(() -> service.byChannel(
                LocalDateTime.of(2026, 8, 12, 0, 0), LocalDateTime.of(2026, 8, 11, 0, 0)))
            .isInstanceOf(ApplicationException.class)
            .hasMessageContaining(PaymentMessage.STATS_INVALID_RANGE.message());
    }

    // ==================== anomalies ====================
    // setUp 用默认阈值 24/48/1 小时构造；下列 mock 与 verify 均以此为准，
    // 验「可配阈值」真的经构造器流入仓储（变异友好）。

    @Test
    @DisplayName("anomalies：装配长时在途 + 近期失败（totalCount 求和）+ 阈值流入仓储")
    void given_stuckAndFailures_when_anomalies_then_assembledAndThresholdsFlowedToRepo() {
        when(statsQueryRepository.longPendingPayments(24L)).thenReturn(new StuckOrderCount(3L, 300L));
        when(statsQueryRepository.longRefundingRefunds(48L)).thenReturn(new StuckOrderCount(1L, 100L));
        when(statsQueryRepository.recentFailureCounts(1L)).thenReturn(List.of(
            new FailureCountByType("PAYMENT_QUERY", 4L),
            new FailureCountByType("PAYMENT_CALLBACK", 2L)
        ));

        AnomaliesResponse result = service.anomalies();

        assertThat(result.longPendingPayments().count()).isEqualTo(3L);
        assertThat(result.longPendingPayments().amount()).isEqualTo(300L);
        assertThat(result.longRefundingRefunds().count()).isEqualTo(1L);
        assertThat(result.longRefundingRefunds().amount()).isEqualTo(100L);
        assertThat(result.recentFailures().totalCount()).isEqualTo(6L);  // 4 + 2
        assertThat(result.recentFailures().byType()).hasSize(2);
        assertThat(result.recentFailures().byType()).extracting(AnomaliesResponse.FailureCount::logType)
            .containsExactly("PAYMENT_QUERY", "PAYMENT_CALLBACK");
        assertThat(result.recentFailures().byType()).extracting(AnomaliesResponse.FailureCount::failureCount)
            .containsExactly(4L, 2L);

        // 验「可配阈值」经构造器流入仓储（变异点：阈值字段/默认值）
        verify(statsQueryRepository).longPendingPayments(24L);
        verify(statsQueryRepository).longRefundingRefunds(48L);
        verify(statsQueryRepository).recentFailureCounts(1L);
    }

    @Test
    @DisplayName("anomalies：自定义阈值（非默认）也正确流入仓储（验「可配置」AC）")
    void given_customThresholds_when_anomalies_then_customHoursFlowToRepo() {
        // 用 2/6/0.5 小时阈值重新构造——验阈值可配、非硬编码
        StatsAppService customService = new StatsAppService(statsQueryRepository, 2, 6, 1);
        when(statsQueryRepository.longPendingPayments(2L)).thenReturn(new StuckOrderCount(0L, 0L));
        when(statsQueryRepository.longRefundingRefunds(6L)).thenReturn(new StuckOrderCount(0L, 0L));
        when(statsQueryRepository.recentFailureCounts(1L)).thenReturn(List.of());

        customService.anomalies();

        verify(statsQueryRepository).longPendingPayments(2L);
        verify(statsQueryRepository).longRefundingRefunds(6L);
    }

    @Test
    @DisplayName("anomalies：空结果全零（仓储 COALESCE 保证不返回 null）")
    void given_empty_when_anomalies_then_allZeros() {
        when(statsQueryRepository.longPendingPayments(24L)).thenReturn(new StuckOrderCount(0L, 0L));
        when(statsQueryRepository.longRefundingRefunds(48L)).thenReturn(new StuckOrderCount(0L, 0L));
        when(statsQueryRepository.recentFailureCounts(1L)).thenReturn(List.of());

        AnomaliesResponse result = service.anomalies();

        assertThat(result.longPendingPayments().count()).isZero();
        assertThat(result.longPendingPayments().amount()).isZero();
        assertThat(result.longRefundingRefunds().count()).isZero();
        assertThat(result.recentFailures().totalCount()).isZero();
        assertThat(result.recentFailures().byType()).isEmpty();
    }

    // ==================== operationsActivity ====================

    @Test
    @DisplayName("operationsActivity：操作员透视（code→name）+ NOTIFY_RESEND 按来源系统归组")
    void given_operatorAndResendCounts_when_operationsActivity_then_pivotedAndGrouped() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 12, 0, 0);

        when(statsQueryRepository.operatorOperationCounts(from, to)).thenReturn(List.of(
            new OperatorOperationCount(1L, "Alice", OperationType.AUDIT_APPROVE.getCode(), 4L),
            new OperatorOperationCount(1L, "Alice", OperationType.NOTIFY_RESEND.getCode(), 2L),
            new OperatorOperationCount(2L, "Bob", OperationType.AUDIT_REJECT.getCode(), 1L)
        ));
        when(statsQueryRepository.notifyResendBySystem(from, to)).thenReturn(List.of(
            new NotifyResendBySystem("edu", 3L),
            new NotifyResendBySystem("shop", 1L)
        ));

        OperationsActivityResponse result = service.operationsActivity(from, to);

        // byOperator：2 个操作员（按仓储返回顺序）
        assertThat(result.byOperator()).hasSize(2);
        OperationsActivityResponse.OperatorActivity alice = result.byOperator().get(0);
        assertThat(alice.operatorId()).isEqualTo(1L);
        assertThat(alice.operatorName()).isEqualTo("Alice");
        assertThat(alice.totalCount()).isEqualTo(6L);  // 4 + 2
        assertThat(alice.operations()).hasSize(2);
        assertThat(alice.operations()).extracting(OperationsActivityResponse.OperationCount::operationName)
            .containsExactly("审核通过", "通知重发");  // code→name 映射
        assertThat(alice.operations()).extracting(OperationsActivityResponse.OperationCount::count)
            .containsExactly(4L, 2L);
        OperationsActivityResponse.OperatorActivity bob = result.byOperator().get(1);
        assertThat(bob.totalCount()).isEqualTo(1L);
        assertThat(bob.operations()).extracting(OperationsActivityResponse.OperationCount::operationName)
            .containsExactly("审核拒绝");

        // notifyResend：totalCount=4，byBusinessSystem 2 个
        assertThat(result.notifyResend().totalCount()).isEqualTo(4L);  // 3 + 1
        assertThat(result.notifyResend().byBusinessSystem()).hasSize(2);
        assertThat(result.notifyResend().byBusinessSystem()).extracting(OperationsActivityResponse.SystemResendCount::businessSystem)
            .containsExactly("edu", "shop");
        assertThat(result.notifyResend().byBusinessSystem()).extracting(OperationsActivityResponse.SystemResendCount::count)
            .containsExactly(3L, 1L);
    }

    @Test
    @DisplayName("operationsActivity：空表返回空列表与零总数")
    void given_empty_when_operationsActivity_then_empty() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 12, 0, 0);

        when(statsQueryRepository.operatorOperationCounts(from, to)).thenReturn(List.of());
        when(statsQueryRepository.notifyResendBySystem(from, to)).thenReturn(List.of());

        OperationsActivityResponse result = service.operationsActivity(from, to);

        assertThat(result.byOperator()).isEmpty();
        assertThat(result.notifyResend().totalCount()).isZero();
        assertThat(result.notifyResend().byBusinessSystem()).isEmpty();
    }

    @Test
    @DisplayName("operationsActivity：operatorId 为 null 的系统动作单独归组（保留 null）")
    void given_nullOperatorId_when_operationsActivity_then_groupedAsNullEntry() {
        LocalDateTime from = LocalDateTime.of(2026, 8, 11, 0, 0);
        LocalDateTime to = LocalDateTime.of(2026, 8, 12, 0, 0);

        when(statsQueryRepository.operatorOperationCounts(from, to)).thenReturn(List.of(
            new OperatorOperationCount(null, null, OperationType.NOTIFY_RESEND.getCode(), 3L)
        ));
        when(statsQueryRepository.notifyResendBySystem(from, to)).thenReturn(List.of(
            new NotifyResendBySystem(null, 3L)  // 来源系统亦为 null
        ));

        OperationsActivityResponse result = service.operationsActivity(from, to);

        assertThat(result.byOperator()).hasSize(1);
        OperationsActivityResponse.OperatorActivity system = result.byOperator().get(0);
        assertThat(system.operatorId()).isNull();  // 保留 null，不丢组
        assertThat(system.operatorName()).isNull();
        assertThat(system.totalCount()).isEqualTo(3L);
        assertThat(result.notifyResend().byBusinessSystem()).hasSize(1);
        assertThat(result.notifyResend().byBusinessSystem().get(0).businessSystem()).isNull();
    }

    @Test
    @DisplayName("operationsActivity：from 晚于 to 抛 STATS_INVALID_RANGE")
    void given_fromAfterTo_when_operationsActivity_then_invalidRange() {
        assertThatThrownBy(() -> service.operationsActivity(
                LocalDateTime.of(2026, 8, 12, 0, 0), LocalDateTime.of(2026, 8, 11, 0, 0)))
            .isInstanceOf(ApplicationException.class)
            .hasMessageContaining(PaymentMessage.STATS_INVALID_RANGE.message());
    }
}
