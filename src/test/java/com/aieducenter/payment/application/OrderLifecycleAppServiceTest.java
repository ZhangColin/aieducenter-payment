package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.response.OrderLifecycleResponse;
import com.aieducenter.payment.application.mapper.OrderLifecycleMapper;
import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.repository.OperationLogRepository;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * OrderLifecycleAppService 测试（AppService Mockito 缝）。
 *
 * <p>沿用 PaymentLogQueryAppServiceTest / OperationLogAppServiceTest 模式：mock 仓储与 Mapper，
 * 构造被测服务直接驱动。Mapper 被 mock → 聚合的 id/createdAt（持久化时由 JPA/TSID 注入）不影响测试；
 * 排序键由 Mapper 返回的 canned DTO 的 createdAt/id 携带，精准验证「合并 + 升序排序」逻辑（变异友好）。</p>
 *
 * <p>覆盖（AC：合并排序逻辑）：① 两源合并、按 createdAt 升序（仓储返回倒序，验证显式升序重排）；
 * ② 相同 createdAt 跨来源按 id 升序稳定； ③ 两源均空返回空列表。</p>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("订单生命周期应用服务测试")
class OrderLifecycleAppServiceTest {

    @Mock private PaymentLogRepository paymentLogRepository;
    @Mock private OperationLogRepository operationLogRepository;
    @Mock private OrderLifecycleMapper orderLifecycleMapper;

    private OrderLifecycleAppService service;

    @BeforeEach
    void setUp() {
        service = new OrderLifecycleAppService(
            paymentLogRepository, operationLogRepository, orderLifecycleMapper
        );
    }

    @Test
    @DisplayName("getLifecycle：合并 GATEWAY 与 OPERATION 事件，按 createdAt 升序返回（仓储倒序输入）")
    void given_interleavedEvents_when_getLifecycle_then_mergedAndSortedByCreatedAtAsc() {
        String orderNo = "PAY20260811001";
        LocalDateTime t1 = LocalDateTime.of(2026, 8, 11, 9, 0, 0);
        LocalDateTime t2 = LocalDateTime.of(2026, 8, 11, 9, 5, 0);
        LocalDateTime t3 = LocalDateTime.of(2026, 8, 11, 9, 10, 0);
        LocalDateTime t4 = LocalDateTime.of(2026, 8, 11, 9, 15, 0);

        // 仓储按 createdAt DESC 返回（既有方法约定）；AppService 须显式重排为 ASC。
        PaymentLog gatewayEarly = gatewayLog(orderNo, "PAYMENT_REQUEST");
        PaymentLog gatewayLate = gatewayLog(orderNo, "PAYMENT_QUERY");
        when(paymentLogRepository.findByPaymentOrderNoOrderByCreatedAtDesc(orderNo))
            .thenReturn(List.of(gatewayLate, gatewayEarly));   // DESC：晚的在前

        OperationLog opMid = operationLog(orderNo, OperationType.AUDIT_APPROVE);
        OperationLog opLast = operationLog(orderNo, OperationType.NOTIFY_RESEND);
        when(operationLogRepository.findByTargetNoOrderByCreatedAtDesc(orderNo))
            .thenReturn(List.of(opLast, opMid));               // DESC：晚的在前

        // Mapper canned DTO：携带排序键（createdAt / id），来源标签 + action 区分。
        OrderLifecycleResponse r1 = event(100L, "GATEWAY", t1, "PAYMENT_REQUEST");
        OrderLifecycleResponse r2 = event(200L, "OPERATION", t2, "AUDIT_APPROVE");
        OrderLifecycleResponse r3 = event(101L, "GATEWAY", t3, "PAYMENT_QUERY");
        OrderLifecycleResponse r4 = event(201L, "OPERATION", t4, "NOTIFY_RESEND");
        when(orderLifecycleMapper.fromPaymentLog(gatewayEarly)).thenReturn(r1);
        when(orderLifecycleMapper.fromPaymentLog(gatewayLate)).thenReturn(r3);
        when(orderLifecycleMapper.fromOperationLog(opMid)).thenReturn(r2);
        when(orderLifecycleMapper.fromOperationLog(opLast)).thenReturn(r4);

        List<OrderLifecycleResponse> result = service.getLifecycle(orderNo);

        assertThat(result).containsExactly(r1, r2, r3, r4);    // 升序：t1<t2<t3<t4（跨来源交错）
        assertThat(result).extracting(OrderLifecycleResponse::source)
            .containsExactly("GATEWAY", "OPERATION", "GATEWAY", "OPERATION");
    }

    @Test
    @DisplayName("getLifecycle：相同 createdAt 跨来源时按 id 升序稳定排序")
    void given_sameCreatedAtAcrossSources_when_getLifecycle_then_sortedByIdAsc() {
        String orderNo = "PAY20260811002";
        LocalDateTime sameTime = LocalDateTime.of(2026, 8, 11, 10, 0, 0);

        // 插入顺序：先 GATEWAY(id=200) 后 OPERATION(id=100)；同 createdAt。
        // 有 thenComparing(id) → [100, 200]；无（仅稳定排序）→ 维持插入序 [200, 100]（变异被杀死）。
        PaymentLog gateway = gatewayLog(orderNo, "PAYMENT_REQUEST");
        when(paymentLogRepository.findByPaymentOrderNoOrderByCreatedAtDesc(orderNo))
            .thenReturn(List.of(gateway));
        OperationLog operation = operationLog(orderNo, OperationType.NOTIFY_RESEND);
        when(operationLogRepository.findByTargetNoOrderByCreatedAtDesc(orderNo))
            .thenReturn(List.of(operation));

        OrderLifecycleResponse gatewayEvent = event(200L, "GATEWAY", sameTime, "PAYMENT_REQUEST");
        OrderLifecycleResponse operationEvent = event(100L, "OPERATION", sameTime, "NOTIFY_RESEND");
        when(orderLifecycleMapper.fromPaymentLog(gateway)).thenReturn(gatewayEvent);
        when(orderLifecycleMapper.fromOperationLog(operation)).thenReturn(operationEvent);

        List<OrderLifecycleResponse> result = service.getLifecycle(orderNo);

        assertThat(result).containsExactly(operationEvent, gatewayEvent);  // id 升序：100 < 200
    }

    @Test
    @DisplayName("getLifecycle：两源均无记录时返回空列表")
    void given_noEvents_when_getLifecycle_then_returnsEmptyList() {
        String orderNo = "PAY20260811003";

        when(paymentLogRepository.findByPaymentOrderNoOrderByCreatedAtDesc(orderNo))
            .thenReturn(List.of());
        when(operationLogRepository.findByTargetNoOrderByCreatedAtDesc(orderNo))
            .thenReturn(List.of());

        List<OrderLifecycleResponse> result = service.getLifecycle(orderNo);

        assertThat(result).isEmpty();
    }

    // ---- 构造器：仅满足 Mapper 调用所需，id/createdAt 由持久化注入、本测试不依赖 ----

    private static PaymentLog gatewayLog(String orderNo, String logType) {
        return new PaymentLog(
            orderNo, null, logType, "ICBC", "qrcode/consumption",
            "https://gw/qrcode", "{}", "{}",
            200, "0", "成功", 300L, Boolean.TRUE, null
        );
    }

    private static OperationLog operationLog(String orderNo, OperationType operation) {
        return new OperationLog(
            OperationLogTargetType.PAYMENT, orderNo, operation,
            123L, "张三", "admin-bff", "SUCCESS", null
        );
    }

    private static OrderLifecycleResponse event(long id, String source, LocalDateTime createdAt, String action) {
        return new OrderLifecycleResponse(
            id, source, createdAt, action, action,
            "SUCCESS", "performer", "performer-system", "detail"
        );
    }
}
