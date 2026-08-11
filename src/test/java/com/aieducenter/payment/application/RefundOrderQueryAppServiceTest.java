package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.RefundOrderQuery;
import com.aieducenter.payment.application.dto.response.RefundOrderResponse;
import com.aieducenter.payment.application.mapper.RefundOrderMapper;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.enums.AuditType;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.repository.RefundOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
 * <p>沿用 PaymentOrderQueryAppServiceTest 模式：mock 仓储与 Mapper，构造被测服务直接驱动。
 * 覆盖：查询参数正确传递（Specification + Pageable）与 PageResponse 响应映射（变异友好）。</p>
 *
 * <p>说明：仓储 findAll 被 mock，Specification 不真正执行（不引入 @DataJpaTest，
 * 与现有代码库一致）；查询正确性靠 @Condition 注解的编译期保证与显式可读性。</p>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("退款订单查询应用服务测试")
class RefundOrderQueryAppServiceTest {

    @Mock private RefundOrderRepository refundOrderRepository;
    @Mock private RefundOrderMapper refundOrderMapper;

    private RefundOrderQueryAppService service;

    @BeforeEach
    void setUp() {
        service = new RefundOrderQueryAppService(refundOrderRepository, refundOrderMapper);
    }

    @Test
    @DisplayName("list：多条件查询分页（含 auditType/auditorId），返回 PageResponse 且仅暴露 DTO，page 为 1-based")
    void given_queryAndPageable_when_list_then_returnsPageResponseOfDtos() {
        RefundOrderQuery query = new RefundOrderQuery(
            "REF20260811", "PAY20260811", "BIZ001", "course-system",
            List.of(RefundStatus.APPROVED, RefundStatus.REFUNDING),
            AuditType.MANUAL, 123L,
            100L, 10000L,
            LocalDateTime.of(2026, 8, 1, 0, 0), LocalDateTime.of(2026, 8, 11, 0, 0)
        );
        Pageable pageable = PageRequest.of(0, 20);

        RefundOrder order = new RefundOrder(
            "BIZ001", "PAY20260811", "course-system", "课程购买",
            10000L, 10000L, "用户申请退款", null, null
        );
        Page<RefundOrder> page = new PageImpl<>(List.of(order), pageable, 1);
        when(refundOrderRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(page);

        RefundOrderResponse responseDto = new RefundOrderResponse(
            1L, "BIZ001", "REF20260811", "PAY20260811", "course-system", "课程购买",
            RefundStatus.APPROVED.getCode(), RefundStatus.APPROVED.getName(),
            10000L, 10000L, "用户申请退款",
            "张三", true, "同意",
            AuditType.MANUAL.getCode(), AuditType.MANUAL.getName(),
            LocalDateTime.now(), LocalDateTime.now(), null, null,
            "BANK_REFUND_001", null
        );
        when(refundOrderMapper.convertList(List.of(order))).thenReturn(List.of(responseDto));

        var result = service.list(query, pageable);

        assertThat(result.items()).containsExactly(responseDto);
        assertThat(result.total()).isEqualTo(1L);
        assertThat(result.page()).isEqualTo(1);          // 0-based → 1-based（+1 变异点）
        assertThat(result.size()).isEqualTo(20);
        verify(refundOrderRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    @DisplayName("list：空查询条件也返回 PageResponse（Specification 不抛错）")
    void given_emptyQuery_when_list_then_returnsPageResponse() {
        RefundOrderQuery query = new RefundOrderQuery(
            null, null, null, null, null, null, null, null, null, null, null
        );
        Pageable pageable = PageRequest.of(2, 10);

        Page<RefundOrder> emptyPage = new PageImpl<>(List.of(), pageable, 0);
        when(refundOrderRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(emptyPage);
        when(refundOrderMapper.convertList(List.of())).thenReturn(List.of());

        var result = service.list(query, pageable);

        assertThat(result.items()).isEmpty();
        assertThat(result.total()).isZero();
        assertThat(result.page()).isEqualTo(3);          // PageRequest.of(2,10) → page 3
        assertThat(result.size()).isEqualTo(10);
    }
}
