package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.response.AnomaliesResponse;
import com.aieducenter.payment.application.dto.response.ByBusinessSystemResponse;
import com.aieducenter.payment.application.dto.response.ByBusinessSystemResponse.BusinessSystemBreakdown;
import com.aieducenter.payment.application.dto.response.ByChannelResponse;
import com.aieducenter.payment.application.dto.response.ByChannelResponse.ChannelBreakdown;
import com.aieducenter.payment.application.dto.response.GatewayHealthResponse;
import com.aieducenter.payment.application.dto.response.GatewayHealthResponse.InterfaceHealth;
import com.aieducenter.payment.application.dto.response.GatewayHealthResponse.ReturnCodeCount;
import com.aieducenter.payment.application.dto.response.OperationsActivityResponse;
import com.aieducenter.payment.application.dto.response.OperationsAuditResponse;
import com.aieducenter.payment.application.dto.response.OperationsAuditResponse.AuditorBreakdown;
import com.aieducenter.payment.application.dto.response.PaymentOverviewResponse;
import com.aieducenter.payment.application.dto.response.PaymentOverviewResponse.Summary;
import com.aieducenter.payment.application.dto.response.PaymentOverviewResponse.TrendBucket;
import com.aieducenter.payment.application.dto.response.StatusDistributionResponse;
import com.aieducenter.payment.application.dto.response.StatusDistributionResponse.Backlog;
import com.aieducenter.payment.application.dto.response.StatusDistributionResponse.PaymentStatusBucket;
import com.aieducenter.payment.application.dto.response.StatusDistributionResponse.RefundStatusBucket;
import com.cartisan.core.domain.BaseEnum;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

/**
 * 统计应用服务（issue #17 统计一档 + issue #18 统计二档）。
 *
 * <p>只读、银行无关。装配逻辑（成功率/通过率计算、除零保护、趋势桶序列补零、净额、
 * returnCode 归组、按审核人/操作员透视、业务系统并集、渠道补零）是 pitest 主料；仓储
 * {@link StatsQueryRepository} 由 jOOQ 读侧实现，本服务只消费其投影。统计范式详见
 * {@code docs/superpowers/specs/2026-08-11-stats-tier1-design.md}
 * 与 {@code docs/superpowers/specs/2026-08-11-stats-tier2-design.md}。</p>
 */
@Service
public class StatsAppService {

    /** 趋势桶数量上限（防御过大的统计区间，超出抛 {@link PaymentMessage#STATS_RANGE_TOO_LARGE}）。 */
    static final int MAX_BUCKETS = 400;

    private static final int RATE_SCALE = 4;
    private static final int DURATION_SCALE = 2;

    private final StatsQueryRepository statsQueryRepository;
    /** anomalies「长时 PENDING 支付单」阈值（小时），见 {@code payment.stats.anomaly.long-pending-payment-hours}。 */
    private final long longPendingPaymentHours;
    /** anomalies「长时 REFUNDING 退款单」阈值（小时），见 {@code payment.stats.anomaly.long-refunding-refund-hours}。 */
    private final long longRefundingRefundHours;
    /** anomalies「近期」失败窗口（小时），见 {@code payment.stats.anomaly.failure-window-hours}。 */
    private final long failureWindowHours;

    /**
     * 显式构造器注入仓储与 3 个 anomalies 阈值（默认 24/48/1 小时）。
     *
     * <p>阈值经构造器注入（而非字段注入）以利缝测试直接传值，避免反射。Spring 自动按类型装配仓储、
     * 按 {@code @Value} 装配基本类型。</p>
     */
    public StatsAppService(
            StatsQueryRepository statsQueryRepository,
            @Value("${payment.stats.anomaly.long-pending-payment-hours:24}") long longPendingPaymentHours,
            @Value("${payment.stats.anomaly.long-refunding-refund-hours:48}") long longRefundingRefundHours,
            @Value("${payment.stats.anomaly.failure-window-hours:1}") long failureWindowHours) {
        this.statsQueryRepository = statsQueryRepository;
        this.longPendingPaymentHours = longPendingPaymentHours;
        this.longRefundingRefundHours = longRefundingRefundHours;
        this.failureWindowHours = failureWindowHours;
    }

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

    /**
     * 按业务系统维度统计（issue #18）：payment / refund 双源按 businessSystemName 并集，
     * 缺失侧补零；refundRate = 退款成功笔数 / 支付成功笔数（跨源，除零保护）。
     */
    @Transactional(readOnly = true)
    public ByBusinessSystemResponse byBusinessSystem(LocalDateTime from, LocalDateTime to) {
        validateRange(from, to);
        List<BusinessSystemPaymentRollup> payments = statsQueryRepository.businessSystemPaymentRollup(from, to);
        List<BusinessSystemRefundRollup> refunds = statsQueryRepository.businessSystemRefundRollup(from, to);

        Map<String, BusinessSystemPaymentRollup> payByName = payments.stream()
                .collect(toMap(BusinessSystemPaymentRollup::businessSystemName, r -> r, (a, b) -> a, LinkedHashMap::new));
        Map<String, BusinessSystemRefundRollup> refundByName = refunds.stream()
                .collect(toMap(BusinessSystemRefundRollup::businessSystemName, r -> r, (a, b) -> a, LinkedHashMap::new));

        // 保序并集：payment 顺序优先，refund-only 的业务系统追加在后
        Set<String> names = new LinkedHashSet<>(payByName.keySet());
        names.addAll(refundByName.keySet());

        List<BusinessSystemBreakdown> breakdowns = new ArrayList<>();
        for (String name : names) {
            BusinessSystemPaymentRollup p = payByName.get(name);
            BusinessSystemRefundRollup r = refundByName.get(name);
            long paidCount = p != null ? p.paidCount() : 0;
            long refundedCount = r != null ? r.refundedCount() : 0;
            ByBusinessSystemResponse.Summary payment = new ByBusinessSystemResponse.Summary(
                    p != null ? p.orderCount() : 0,
                    p != null ? p.totalAmount() : 0,
                    paidCount,
                    p != null ? p.paidAmount() : 0,
                    rate(paidCount, p != null ? p.orderCount() : 0));
            ByBusinessSystemResponse.Summary refund = new ByBusinessSystemResponse.Summary(
                    r != null ? r.orderCount() : 0,
                    r != null ? r.totalAmount() : 0,
                    refundedCount,
                    r != null ? r.refundedAmount() : 0,
                    rate(refundedCount, r != null ? r.orderCount() : 0));
            // refundRate 跨源：refundedCount / paidCount
            BigDecimal refundRate = rate(refundedCount, paidCount);
            breakdowns.add(new BusinessSystemBreakdown(name, payment, refund, refundRate));
        }
        return new ByBusinessSystemResponse(breakdowns);
    }

    /**
     * 按渠道维度统计（issue #18）：pay_mode / access_type 两路独立聚合，按枚举顺序补零，
     * Integer code 映射回枚举名。仅统计对应列非空的支付单。
     */
    @Transactional(readOnly = true)
    public ByChannelResponse byChannel(LocalDateTime from, LocalDateTime to) {
        validateRange(from, to);
        List<ChannelPaymentRollup> payModeRollups = statsQueryRepository.payModeRollup(from, to);
        List<ChannelPaymentRollup> accessTypeRollups = statsQueryRepository.accessTypeRollup(from, to);

        Map<Integer, String> payModeNames = enumNames(PayMode.values());
        Map<Integer, String> accessTypeNames = enumNames(AccessType.values());
        List<Integer> payModeCodes = enumCodes(PayMode.values());
        List<Integer> accessTypeCodes = enumCodes(AccessType.values());

        return new ByChannelResponse(
                buildChannelBreakdowns(payModeRollups, payModeCodes, payModeNames),
                buildChannelBreakdowns(accessTypeRollups, accessTypeCodes, accessTypeNames));
    }

    /**
     * 异常监控（issue #18）：长时 PENDING 支付单 / 长时 REFUNDING 退款单 / 近期查询回调失败。
     *
     * <p>无窗口参数——「当前时间」与「长时」cutoff 均由 SQL 的 {@code NOW()} 计算；阈值（小时）经
     * 构造器注入并传给仓储。仓储已 {@code COALESCE} 保证滞留计数非 null。</p>
     */
    @Transactional(readOnly = true)
    public AnomaliesResponse anomalies() {
        StuckOrderCount pending = statsQueryRepository.longPendingPayments(longPendingPaymentHours);
        StuckOrderCount refunding = statsQueryRepository.longRefundingRefunds(longRefundingRefundHours);
        List<FailureCountByType> failures = statsQueryRepository.recentFailureCounts(failureWindowHours);

        long totalFailures = 0;
        List<AnomaliesResponse.FailureCount> byType = new ArrayList<>();
        for (FailureCountByType f : failures) {
            totalFailures += f.failureCount();
            byType.add(new AnomaliesResponse.FailureCount(f.logType(), f.failureCount()));
        }
        return new AnomaliesResponse(
                new AnomaliesResponse.StuckOrders(pending.orderCount(), pending.totalAmount()),
                new AnomaliesResponse.StuckOrders(refunding.orderCount(), refunding.totalAmount()),
                new AnomaliesResponse.RecentFailures(totalFailures, byType));
    }

    /**
     * 操作活跃度（issue #18）：各操作员的操作类型/笔数分布 + 通知重发次数及来源业务系统。
     * 数据源 OperationLog（单一）。
     */
    @Transactional(readOnly = true)
    public OperationsActivityResponse operationsActivity(LocalDateTime from, LocalDateTime to) {
        validateRange(from, to);
        List<OperatorOperationCount> opCounts = statsQueryRepository.operatorOperationCounts(from, to);
        List<NotifyResendBySystem> resendCounts = statsQueryRepository.notifyResendBySystem(from, to);

        Map<Integer, String> operationNames = enumNames(OperationType.values());
        return new OperationsActivityResponse(
                buildOperatorActivity(opCounts, operationNames),
                buildNotifyResend(resendCounts));
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

    /**
     * 按渠道聚合：rollup 按 code 索引，按 {@code codes}（枚举顺序）补零，code 映射回 {@code names}。
     */
    private static List<ChannelBreakdown> buildChannelBreakdowns(List<ChannelPaymentRollup> rollups,
                                                                 List<Integer> codes, Map<Integer, String> names) {
        Map<Integer, ChannelPaymentRollup> byCode = rollups.stream()
                .collect(toMap(ChannelPaymentRollup::channelCode, r -> r, (a, b) -> a));
        List<ChannelBreakdown> result = new ArrayList<>();
        for (Integer code : codes) {
            ChannelPaymentRollup r = byCode.get(code);
            long count = r != null ? r.orderCount() : 0;
            long paidCount = r != null ? r.paidCount() : 0;
            result.add(new ChannelBreakdown(code, names.get(code),
                    count, r != null ? r.totalAmount() : 0,
                    paidCount, r != null ? r.paidAmount() : 0,
                    rate(paidCount, count)));
        }
        return result;
    }

    /** 枚举 code 序列（按 {@code values()} 顺序）。 */
    private static List<Integer> enumCodes(BaseEnum<?>[] values) {
        return Arrays.stream(values).map(BaseEnum::getCode).toList();
    }

    /** 枚举 code→name 映射（保序）。 */
    private static Map<Integer, String> enumNames(BaseEnum<?>[] values) {
        return Arrays.stream(values)
                .collect(toMap(BaseEnum::getCode, BaseEnum::getName, (a, b) -> a, LinkedHashMap::new));
    }

    /**
     * 操作员透视：按 {@code operatorId} 归组 operation 行（仓储返回顺序保序，含 null 操作员）。
     */
    private static List<OperationsActivityResponse.OperatorActivity> buildOperatorActivity(
            List<OperatorOperationCount> counts, Map<Integer, String> operationNames) {
        Map<Long, String> nameById = new HashMap<>();
        Map<Long, Long> totalById = new HashMap<>();
        Map<Long, List<OperationsActivityResponse.OperationCount>> opsById = new LinkedHashMap<>();
        for (OperatorOperationCount c : counts) {
            nameById.put(c.operatorId(), c.operatorName());
            totalById.merge(c.operatorId(), c.opCount(), Long::sum);
            opsById.computeIfAbsent(c.operatorId(), k -> new ArrayList<>())
                    .add(new OperationsActivityResponse.OperationCount(
                            c.operation(), operationNames.get(c.operation()), c.opCount()));
        }
        List<OperationsActivityResponse.OperatorActivity> result = new ArrayList<>();
        for (Long id : opsById.keySet()) {
            result.add(new OperationsActivityResponse.OperatorActivity(
                    id, nameById.get(id), totalById.get(id), opsById.get(id)));
        }
        return result;
    }

    /** 通知重发汇总：求和总数 + 透传按来源业务系统归组。 */
    private static OperationsActivityResponse.NotifyResendActivity buildNotifyResend(
            List<NotifyResendBySystem> counts) {
        long total = 0;
        List<OperationsActivityResponse.SystemResendCount> bySystem = new ArrayList<>();
        for (NotifyResendBySystem c : counts) {
            total += c.resendCount();
            bySystem.add(new OperationsActivityResponse.SystemResendCount(c.operatorSystem(), c.resendCount()));
        }
        return new OperationsActivityResponse.NotifyResendActivity(total, bySystem);
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
