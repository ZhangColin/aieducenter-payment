package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.RefundOrderQuery;
import com.aieducenter.payment.application.dto.response.RefundOrderResponse;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.enums.AuditType;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.repository.RefundOrderRepository;
import com.cartisan.web.request.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * RefundOrderQueryAppService 测试（AppService Mockito 缝）。
 *
 * <p>沿用 PaymentOrderQueryAppServiceTest 模式：mock 仓储，构造被测服务直接驱动。
 * 覆盖：Pagination（wire 1-based）→ Spring 0-based PageRequest 换算与 PageResponse.of 回显；
 * DTO 转换走 mapper static convert 真源（含 auditType 空安全），不 mock。</p>
 *
 * <p>说明：仓储 findAll 被 mock，Specification 不真正执行（不引入 @DataJpaTest，
 * 与现有代码库一致）；查询正确性靠 @Condition 注解的编译期保证与显式可读性。</p>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("退款订单查询应用服务测试")
class RefundOrderQueryAppServiceTest {

    @Mock private RefundOrderRepository refundOrderRepository;

    private RefundOrderQueryAppService service;

    @BeforeEach
    void setUp() {
        service = new RefundOrderQueryAppService(refundOrderRepository);
    }

    @Test
    @DisplayName("list：多条件查询（含 auditType/auditorId），wire page=1 转 0-based，回显 1-based")
    void given_queryAndFirstPage_when_list_then_zeroBasedConversionAndOneBasedEcho() {
        RefundOrderQuery query = new RefundOrderQuery(
            "REF20260811", "PAY20260811", "BIZ001", "course-system",
            List.of(RefundStatus.APPROVED, RefundStatus.REFUNDING),
            AuditType.MANUAL, 123L,
            100L, 10000L,
            LocalDateTime.of(2026, 8, 1, 0, 0), LocalDateTime.of(2026, 8, 11, 0, 0)
        );
        Pagination pagination = new Pagination(1, 20, List.of());

        RefundOrder order = new RefundOrder(
            "BIZ001", "PAY20260811", "course-system", "课程购买",
            10000L, 10000L, "用户申请退款", null, null
        );
        when(refundOrderRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenAnswer(invocation -> new PageImpl<>(List.of(order), invocation.getArgument(1), 1));

        var result = service.list(query, pagination);

        Pageable captured = capturedPageable();
        assertThat(captured.getPageNumber()).isZero();   // wire 1-based → Spring 0-based（换算变异点）
        assertThat(captured.getPageSize()).isEqualTo(20);

        assertThat(result.items()).hasSize(1);
        assertThat(result.total()).isEqualTo(1L);
        assertThat(result.page()).isEqualTo(1);          // 回显 1-based（+1 收在 PageResponse.of）
        assertThat(result.size()).isEqualTo(20);

        RefundOrderResponse row = result.items().get(0);
        assertThat(row.status()).isEqualTo(RefundStatus.PENDING.getCode());
        assertThat(row.statusName()).isEqualTo(RefundStatus.PENDING.getName());
        assertThat(row.auditType()).isNull();            // PENDING 态 auditType 空安全（真源行为）
        assertThat(row.auditTypeName()).isNull();
    }

    @Test
    @DisplayName("list：空查询条件 + wire page=3，转 0-based 第 2 页，超尾页原样回显 3")
    void given_emptyQueryAndThirdPage_when_list_then_zeroBasedSecondPageAndEchoesThree() {
        RefundOrderQuery query = new RefundOrderQuery(
            null, null, null, null, null, null, null, null, null, null, null
        );
        Pagination pagination = new Pagination(3, 10, List.of());

        when(refundOrderRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenAnswer(invocation -> new PageImpl<>(List.of(), invocation.getArgument(1), 0));

        var result = service.list(query, pagination);

        Pageable captured = capturedPageable();
        assertThat(captured.getPageNumber()).isEqualTo(2);
        assertThat(captured.getPageSize()).isEqualTo(10);

        assertThat(result.items()).isEmpty();
        assertThat(result.total()).isZero();
        assertThat(result.page()).isEqualTo(3);          // 超尾页原样回显请求页码
        assertThat(result.size()).isEqualTo(10);
    }

    private Pageable capturedPageable() {
        ArgumentCaptor<Pageable> captor = ArgumentCaptor.forClass(Pageable.class);
        verify(refundOrderRepository).findAll(any(Specification.class), captor.capture());
        return captor.getValue();
    }
}
