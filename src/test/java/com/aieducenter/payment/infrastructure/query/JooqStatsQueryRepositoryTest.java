package com.aieducenter.payment.infrastructure.query;

import com.aieducenter.payment.domain.enums.StatsGranularity;
import org.jooq.SQLDialect;
import org.jooq.exception.DetachedException;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * {@link JooqStatsQueryRepository} 无 DB 回归测试（parse + bind 缝）。
 *
 * <p><b>动机</b>：曾因 {@code dsl.resultQuery}（plain SQL）不注册命名参数，所有
 * {@code q.bind(name, value)} 在首次真实执行即抛 {@code IllegalArgumentException:
 * No such parameter}——而 AppService 缝的 Mockito mock 盖不住真实 SQL 路径，该类
 * bug 无自动防护（线上 /api/v1/stats/anomalies 首次暴露）。</p>
 *
 * <p><b>缝的选择</b>：不引入 @DataJpaTest（沿用 spec 约定），仅以无连接的
 * {@code DSL.using(POSTGRES)} 构造仓储，逐方法调用并断言抛 {@link DetachedException}
 * （"No JDBC Connection configured"）——它证明 <b>parser 解析成功且全部命名绑定已完成</b>，
 * 只差数据库连接；若回归为 {@code IllegalArgumentException}（绑定名与 SQL 参数名不一致）
 * 或 {@code ParserException}（SQL 不被 parser 接受），本测试变红。不验证 SQL 的业务正确性。</p>
 */
@DisplayName("JooqStatsQueryRepository：命名绑定与 SQL 参数名一致（无 DB，仅 parse+bind）")
class JooqStatsQueryRepositoryTest {

    /** 无连接 DSLContext：查询构造/绑定可用，execute 阶段抛 DetachedException。 */
    private static final JooqStatsQueryRepository REPO =
        new JooqStatsQueryRepository(DSL.using(SQLDialect.POSTGRES));

    private static final LocalDateTime FROM = LocalDateTime.of(2026, 8, 1, 0, 0);
    private static final LocalDateTime TO = LocalDateTime.of(2026, 8, 19, 0, 0);

    static Stream<Arguments> cases() {
        return Stream.of(
            Arguments.of("countPaymentByStatus（有窗口）", (ThrowingCallable) () -> REPO.countPaymentByStatus(FROM, TO)),
            Arguments.of("countPaymentByStatus（无窗口）", (ThrowingCallable) () -> REPO.countPaymentByStatus(null, null)),
            Arguments.of("countRefundByStatus（有窗口）", (ThrowingCallable) () -> REPO.countRefundByStatus(FROM, TO)),
            Arguments.of("paymentTrend", (ThrowingCallable) () -> REPO.paymentTrend(FROM, TO, StatsGranularity.DAY)),
            Arguments.of("paymentTrend（小时粒度）", (ThrowingCallable) () -> REPO.paymentTrend(FROM, TO, StatsGranularity.HOUR)),
            Arguments.of("refundTrend", (ThrowingCallable) () -> REPO.refundTrend(FROM, TO, StatsGranularity.DAY)),
            Arguments.of("gatewayInterfaceRollup", (ThrowingCallable) () -> REPO.gatewayInterfaceRollup(FROM, TO)),
            Arguments.of("gatewayReturnCodeCounts", (ThrowingCallable) () -> REPO.gatewayReturnCodeCounts(FROM, TO)),
            Arguments.of("auditOperationCounts", (ThrowingCallable) () -> REPO.auditOperationCounts(FROM, TO)),
            Arguments.of("auditorAuditCounts", (ThrowingCallable) () -> REPO.auditorAuditCounts(FROM, TO)),
            Arguments.of("avgAuditDuration", (ThrowingCallable) () -> REPO.avgAuditDuration(FROM, TO)),
            Arguments.of("businessSystemPaymentRollup", (ThrowingCallable) () -> REPO.businessSystemPaymentRollup(FROM, TO)),
            Arguments.of("businessSystemRefundRollup", (ThrowingCallable) () -> REPO.businessSystemRefundRollup(FROM, TO)),
            Arguments.of("payModeRollup", (ThrowingCallable) () -> REPO.payModeRollup(FROM, TO)),
            Arguments.of("accessTypeRollup", (ThrowingCallable) () -> REPO.accessTypeRollup(FROM, TO)),
            Arguments.of("longPendingPayments", (ThrowingCallable) () -> REPO.longPendingPayments(24)),
            Arguments.of("longRefundingRefunds", (ThrowingCallable) () -> REPO.longRefundingRefunds(48)),
            Arguments.of("recentFailureCounts", (ThrowingCallable) () -> REPO.recentFailureCounts(6)),
            Arguments.of("operatorOperationCounts", (ThrowingCallable) () -> REPO.operatorOperationCounts(FROM, TO)),
            Arguments.of("notifyResendBySystem", (ThrowingCallable) () -> REPO.notifyResendBySystem(FROM, TO))
        );
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("cases")
    void given_fixedSql_when_query_then_parseAndBindSucceedBeforeConnection(String label, ThrowingCallable call) {
        // DetachedException = 解析+绑定已完成、仅缺 JDBC 连接；
        // IllegalArgumentException("No such parameter") / ParserException = 回归，测试变红。
        assertThatThrownBy(call)
            .as("查询 [%s] 应在绑定完成后因无连接而抛 DetachedException", label)
            .isInstanceOf(DetachedException.class);
    }
}
