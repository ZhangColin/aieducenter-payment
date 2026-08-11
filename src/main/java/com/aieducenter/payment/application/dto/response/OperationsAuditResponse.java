package com.aieducenter.payment.application.dto.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * 退款审核工作情况响应（GET /api/v1/stats/operations/audit，issue #17）。
 *
 * <p>数据源拆分：笔数/通过率/按审核人 ← OperationLog；平均审核时长 ← RefundOrder
 * （{@code audited_at - created_at}，{@code audit_type=MANUAL}）。
 * {@code byAuditor} 不含人均时长（避免跨聚合归属歧义）。</p>
 *
 * @param totalAudits            审核笔数（AUDIT_APPROVE + AUDIT_REJECT）
 * @param approvedCount          通过数
 * @param rejectedCount          拒绝数
 * @param approvalRate           通过率 [0,1] 4 位小数
 * @param avgAuditDurationMinutes 平均审核时长（分钟，2 位小数）
 * @param byAuditor              按审核人聚合
 */
public record OperationsAuditResponse(
        long totalAudits,
        long approvedCount,
        long rejectedCount,
        BigDecimal approvalRate,
        BigDecimal avgAuditDurationMinutes,
        List<AuditorBreakdown> byAuditor
) {
    public record AuditorBreakdown(
            Long auditorId, String auditorName,
            long count, long approvedCount, long rejectedCount, BigDecimal approvalRate
    ) {
    }
}
