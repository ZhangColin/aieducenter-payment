package com.aieducenter.payment.domain.enums;

import com.cartisan.core.domain.BaseEnum;

import java.time.LocalDateTime;

/**
 * 统计趋势分桶粒度（issue #17）。
 *
 * <p>仅作查询参数，不持久化（无 JpaConverter）。沿用项目 Integer code 枚举约定，
 * 便于前端与其他枚举一致地以 code 传参。{@link #sqlLiteral()} 给 PostgreSQL
 * {@code date_trunc} 的第一参（白名单字面量，防注入）。</p>
 */
public enum StatsGranularity implements BaseEnum<StatsGranularity> {
    DAY(1, "按日"),
    HOUR(2, "按小时");

    private final Integer code;
    private final String name;

    StatsGranularity(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * PostgreSQL {@code date_trunc} 的精度字面量。
     */
    public String sqlLiteral() {
        return this == DAY ? "day" : "hour";
    }

    /**
     * 把时间戳截断到所在桶的起点：DAY → 当天 00:00；HOUR → 整点。
     * 与 SQL {@code date_trunc(sqlLiteral(), created_at)} 语义一致，供 AppService 补零序列对齐。
     */
    public LocalDateTime truncateToBucket(LocalDateTime time) {
        return this == DAY ? time.toLocalDate().atStartOfDay() : time.withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 下一桶起点。
     */
    public LocalDateTime nextBucket(LocalDateTime bucket) {
        return this == DAY ? bucket.plusDays(1) : bucket.plusHours(1);
    }
}
