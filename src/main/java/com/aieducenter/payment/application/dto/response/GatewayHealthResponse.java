package com.aieducenter.payment.application.dto.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * 银行网关健康度响应（GET /api/v1/stats/gateway/health，issue #17，数据源 PaymentLog）。
 *
 * @param interfaces 各接口汇总（含 returnCode 分布）
 */
public record GatewayHealthResponse(List<InterfaceHealth> interfaces) {
    /**
     * @param bankCode           银行编码
     * @param bankInterface      银行接口名
     * @param totalCount         调用次数
     * @param successCount       成功次数
     * @param successRate        成功率 [0,1] 4 位小数
     * @param avgExecutionTimeMs 平均耗时毫秒（2 位小数）
     * @param returnCodes        returnCode 分布
     */
    public record InterfaceHealth(
            String bankCode, String bankInterface,
            long totalCount, long successCount, BigDecimal successRate, BigDecimal avgExecutionTimeMs,
            List<ReturnCodeCount> returnCodes
    ) {
    }

    public record ReturnCodeCount(String returnCode, long count) {
    }
}
