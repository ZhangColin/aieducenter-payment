package com.aieducenter.payment.infrastructure.query.projection;

import java.math.BigDecimal;

/**
 * 网关各接口汇总读模型（issue #17，数据源 PaymentLog）。
 *
 * <p>jOOQ {@code fetchInto} 目标。按 {@code bankInterface, bankCode} 分组。</p>
 *
 * @param bankCode         银行编码
 * @param bankInterface    银行接口名
 * @param totalCount       调用次数
 * @param successCount     成功次数（SUM(CASE WHEN success THEN 1 ELSE 0 END)）
 * @param avgExecutionTime 平均耗时毫秒（AVG(execution_time)）
 */
public record GatewayInterfaceRollup(
        String bankCode, String bankInterface, Long totalCount, Long successCount, BigDecimal avgExecutionTime) {
}
