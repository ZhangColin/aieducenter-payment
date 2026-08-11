package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.response.GatewayHealthResponse;
import com.aieducenter.payment.application.dto.response.GatewayHealthResponse.InterfaceHealth;
import com.aieducenter.payment.application.dto.response.GatewayHealthResponse.ReturnCodeCount;
import com.aieducenter.payment.application.dto.response.OperationsAuditResponse;
import com.aieducenter.payment.application.dto.response.OperationsAuditResponse.AuditorBreakdown;
import com.aieducenter.payment.application.dto.response.PaymentOverviewResponse;
import com.aieducenter.payment.application.dto.response.PaymentOverviewResponse.Summary;
import com.aieducenter.payment.application.dto.response.PaymentOverviewResponse.TrendBucket;
import com.aieducenter.payment.application.dto.response.StatusDistributionResponse;
import com.aieducenter.payment.application.dto.response.StatusDistributionResponse.Backlog;
import com.aieducenter.payment.application.dto.response.StatusDistributionResponse.PaymentStatusBucket;
import com.aieducenter.payment.application.dto.response.StatusDistributionResponse.RefundStatusBucket;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

/**
 * 统计应用服务（issue #17，统计一档）。
 *
 * <p>只读、银行无关。装配逻辑（成功率/通过率计算、除零保护、趋势桶序列补零、净额、
 * returnCode 归组、按审核人透视）是 pitest 主料；仓储 {@link StatsQueryRepository} 由
 * jOOQ 读侧实现，本服务只消费其投影。统计范式详见
 * {@code docs/superpowers/specs/2026-08-11-stats-tier1-design.md}。</p>
 */
@Service
@RequiredArgsConstructor
public class StatsAppService {

    /** 趋势桶数量上限（防御过大的统计区间，超出抛 {@link PaymentMessage#STATS_RANGE_TOO_LARGE}）。 */
    static final int MAX_BUCKETS = 400;

    private static final int RATE_SCALE = 4;
    private static final int DURATION_SCALE = 2;

    private final StatsQueryRepository statsQueryRepository;

    /**
     * 支付交易概览：支付/退款摘要 + 净额 + 趋势序列。
     */
    @Transactional(readOnly = true)
    public PaymentOverviewResponse overview(LocalDateTime from, LocalDateTime to, StatsGranularity granularity) {
        validateRange(from, to);
        StatsGranularity resolved = granularity != null ? granularity : StatsGranularity.DAY;
        List<LocalDateTime> buckets = generateBuckets(from, to, resolved);

        List<PaymentStatusCount> paymentCounts = statsQueryRepository.countPaymentByStatus(from, to);
        List<RefundStatusCount> refundCounts = statsQueryRepository.countRefundByStatus(from, to);
        Summary payment = summarizePayment(paymentCounts);
        Summary refund = summarizeRefund(refundCounts);
        long netAmount = payment.successAmount() - refund.successAmount();

        List<TrendBucket> trend = buildTrend(buckets, from, to, resolved);
        return new PaymentOverviewResponse(payment, refund, netAmount, trend);
    }

    /**
     * 订单状态在途分布：全局快照（无时间窗），所有状态枚举值补零，退款 PENDING 积压单列。
     */
    @Transactional(readOnly = true)
    public StatusDistributionResponse statusDistribution() {
        List<PaymentStatusCount> paymentCounts = statsQueryRepository.countPaymentByStatus(null, null);
        List<RefundStatusCount> refundCounts = statsQueryRepository.countRefundByStatus(null, null);

        Map<Integer, PaymentStatusCount> paymentByCode = paymentCounts.stream()
                .collect(toMap(PaymentStatusCount::status, c -> c));
        Map<Integer, RefundStatusCount> refundByCode = refundCounts.stream()
                .collect(toMap(RefundStatusCount::status, c -> c));

        List<PaymentStatusBucket> paymentBuckets = new ArrayList<>();
        for (PaymentStatus s : PaymentStatus.values()) {
            PaymentStatusCount c = paymentByCode.get(s.getCode());
            paymentBuckets.add(new PaymentStatusBucket(s.getCode(), s.getName(),
                    c != null ? c.orderCount() : 0,
                    c != null ? c.totalAmount() : 0));
        }

        List<RefundStatusBucket> refundBuckets = new ArrayList<>();
        for (RefundStatus s : RefundStatus.values()) {
            RefundStatusCount c = refundByCode.get(s.getCode());
            refundBuckets.add(new RefundStatusBucket(s.getCode(), s.getName(),
                    c != null ? c.orderCount() : 0,
                    c != null ? c.totalAmount() : 0));
        }

        return new StatusDistributionResponse(paymentBuckets, refundBuckets, refundBacklog(refundCounts));
    }

    /**
     * 银行网关健康度：各 bankInterface 调用次数/成功率/平均耗时 + returnCode 分布。
     */
    @Transactional(readOnly = true)
    public GatewayHealthResponse gatewayHealth(LocalDateTime from, LocalDateTime to) {
        validateRange(from, to);
        List<GatewayInterfaceRollup> rollups = statsQueryRepository.gatewayInterfaceRollup(from, to);
        Map<String, List<GatewayReturnCodeCount>> codesByInterface =
                statsQueryRepository.gatewayReturnCodeCounts(from, to).stream()
                        .collect(groupingBy(GatewayReturnCodeCount::bankInterface));

        List<InterfaceHealth> interfaces = new ArrayList<>();
        for (GatewayInterfaceRollup r : rollups) {
            List<ReturnCodeCount> returnCodes = codesByInterface
                    .getOrDefault(r.bankInterface(), List.of()).stream()
                    .map(c -> new ReturnCodeCount(c.returnCode(), c.codeCount()))
                    .toList();
            interfaces.add(new InterfaceHealth(r.bankCode(), r.bankInterface(),
                    r.totalCount(), r.successCount(),
                    rate(r.successCount(), r.totalCount()),
                    r.avgExecutionTime().setScale(DURATION_SCALE, RoundingMode.HALF_UP),
                    returnCodes));
        }
        return new GatewayHealthResponse(interfaces);
    }

    /**
     * 退款审核工作情况：总览（笔数/通过率/平均审核时长）+ 按审核人聚合。
     */
    @Transactional(readOnly = true)
    public OperationsAuditResponse operationsAudit(LocalDateTime from, LocalDateTime to) {
        validateRange(from, to);
        List<AuditOperationCount> opCounts = statsQueryRepository.auditOperationCounts(from, to);
        List<AuditorAuditCount> auditorCounts = statsQueryRepository.auditorAuditCounts(from, to);
        BigDecimal avgDuration = statsQueryRepository.avgAuditDuration(from, to);

        long approved = pickOpCount(opCounts, OperationType.AUDIT_APPROVE.getCode());
        long rejected = pickOpCount(opCounts, OperationType.AUDIT_REJECT.getCode());
        long total = approved + rejected;

        BigDecimal avgDurationMinutes = (avgDuration != null ? avgDuration : BigDecimal.ZERO)
                .setScale(DURATION_SCALE, RoundingMode.HALF_UP);
        List<AuditorBreakdown> byAuditor = buildAuditorBreakdown(auditorCounts);
        return new OperationsAuditResponse(total, approved, rejected, rate(approved, total),
                avgDurationMinutes, byAuditor);
    }

    // ==================== helpers ====================

    private static void validateRange(LocalDateTime from, LocalDateTime to) {
        if (from == null || to == null || from.isAfter(to)) {
            throw new ApplicationException(PaymentMessage.STATS_INVALID_RANGE);
        }
    }

    /**
     * 生成完整连续桶序列（缺失桶由 {@link #buildTrend} 补零）。
     */
    static List<LocalDateTime> generateBuckets(LocalDateTime from, LocalDateTime to, StatsGranularity granularity) {
        LocalDateTime cursor = granularity.truncateToBucket(from);
        List<LocalDateTime> buckets = new ArrayList<>();
        while (!cursor.isAfter(to)) {
            buckets.add(cursor);
            cursor = granularity.nextBucket(cursor);
        }
        if (buckets.size() > MAX_BUCKETS) {
            throw new ApplicationException(PaymentMessage.STATS_RANGE_TOO_LARGE);
        }
        return buckets;
    }

    /**
     * 成功率 [0,1] 4 位小数；分母为 0 时返回 0.0000（除零保护）。
     */
    static BigDecimal rate(long numerator, long denominator) {
        if (denominator == 0) {
            return BigDecimal.ZERO.setScale(RATE_SCALE, RoundingMode.HALF_UP);
        }
        return BigDecimal.valueOf(numerator).divide(BigDecimal.valueOf(denominator), RATE_SCALE, RoundingMode.HALF_UP);
    }

    private static Summary summarizePayment(List<PaymentStatusCount> counts) {
        long total = 0;
        long amount = 0;
        long successCount = 0;
        long successAmount = 0;
        for (PaymentStatusCount c : counts) {
            total += c.orderCount();
            amount += c.totalAmount();
            if (PaymentStatus.PAID.getCode().equals(c.status())) {
                successCount = c.orderCount();
                successAmount = c.paidAmount();
            }
        }
        return new Summary(total, amount, successCount, successAmount, rate(successCount, total));
    }

    private static Summary summarizeRefund(List<RefundStatusCount> counts) {
        long total = 0;
        long amount = 0;
        long successCount = 0;
        long successAmount = 0;
        for (RefundStatusCount c : counts) {
            total += c.orderCount();
            amount += c.totalAmount();
            if (RefundStatus.SUCCESS.getCode().equals(c.status())) {
                successCount = c.orderCount();
                successAmount = c.totalAmount();
            }
        }
        return new Summary(total, amount, successCount, successAmount, rate(successCount, total));
    }

    private static Backlog refundBacklog(List<RefundStatusCount> counts) {
        long pendingCount = 0;
        long pendingAmount = 0;
        for (RefundStatusCount c : counts) {
            if (RefundStatus.PENDING.getCode().equals(c.status())) {
                pendingCount = c.orderCount();
                pendingAmount = c.totalAmount();
            }
        }
        return new Backlog(pendingCount, pendingAmount);
    }

    private static long pickOpCount(List<AuditOperationCount> counts, Integer code) {
        for (AuditOperationCount c : counts) {
            if (code.equals(c.operation())) {
                return c.opCount();
            }
        }
        return 0;
    }

    private static List<AuditorBreakdown> buildAuditorBreakdown(List<AuditorAuditCount> counts) {
        Integer approveCode = OperationType.AUDIT_APPROVE.getCode();
        Integer rejectCode = OperationType.AUDIT_REJECT.getCode();
        Map<Long, Long> approvedById = new HashMap<>();
        Map<Long, Long> rejectedById = new HashMap<>();
        Map<Long, String> nameById = new HashMap<>();
        Set<Long> order = new LinkedHashSet<>();
        for (AuditorAuditCount c : counts) {
            order.add(c.auditorId());
            nameById.put(c.auditorId(), c.auditorName());
            if (approveCode.equals(c.operation())) {
                approvedById.put(c.auditorId(), c.opCount());
            }
            if (rejectCode.equals(c.operation())) {
                rejectedById.put(c.auditorId(), c.opCount());
            }
        }
        List<AuditorBreakdown> result = new ArrayList<>();
        for (Long id : order) {
            long approved = approvedById.getOrDefault(id, 0L);
            long rejected = rejectedById.getOrDefault(id, 0L);
            long count = approved + rejected;
            result.add(new AuditorBreakdown(id, nameById.get(id), count, approved, rejected, rate(approved, count)));
        }
        return result;
    }

    private List<TrendBucket> buildTrend(List<LocalDateTime> buckets, LocalDateTime from, LocalDateTime to,
                                         StatsGranularity granularity) {
        Map<LocalDateTime, PaymentTrendBucket> payByBucket = statsQueryRepository.paymentTrend(from, to, granularity)
                .stream().collect(toMap(PaymentTrendBucket::bucketStart, b -> b));
        Map<LocalDateTime, RefundTrendBucket> refundByBucket = statsQueryRepository.refundTrend(from, to, granularity)
                .stream().collect(toMap(RefundTrendBucket::bucketStart, b -> b));
        List<TrendBucket> trend = new ArrayList<>(buckets.size());
        for (LocalDateTime bucket : buckets) {
            PaymentTrendBucket p = payByBucket.get(bucket);
            RefundTrendBucket r = refundByBucket.get(bucket);
            trend.add(new TrendBucket(bucket,
                    p != null ? p.orderCount() : 0,
                    p != null ? p.totalAmount() : 0,
                    p != null ? p.paidCount() : 0,
                    p != null ? p.paidAmount() : 0,
                    r != null ? r.orderCount() : 0,
                    r != null ? r.totalAmount() : 0,
                    r != null ? r.refundedCount() : 0,
                    r != null ? r.refundedAmount() : 0));
        }
        return trend;
    }
}
