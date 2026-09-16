package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.PaymentOrderQuery;
import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PayMode;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.cartisan.web.request.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
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
 * PaymentOrderQueryAppService 测试（AppService Mockito 缝）。
 *
 * <p>沿用 OperationLogAppServiceTest 模式：mock 仓储，构造被测服务直接驱动。
 * 覆盖：Pagination（wire 1-based）→ Spring 0-based PageRequest 换算（ArgumentCaptor
 * 钉死换算变异点）与 PageResponse.of 回显（1-based、超尾页原样回显）。
 * DTO 转换走 mapper static convert 真源，不 mock。</p>
 *
 * <p>说明：仓储 findAll 被 mock，Specification 不真正执行（不引入 @DataJpaTest，
 * 与现有代码库一致）；查询正确性靠 @Condition 注解的编译期保证与显式可读性。</p>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("支付订单查询应用服务测试")
class PaymentOrderQueryAppServiceTest {

    @Mock private PaymentOrderRepository paymentOrderRepository;

    private PaymentOrderQueryAppService service;

    @BeforeEach
    void setUp() {
        service = new PaymentOrderQueryAppService(paymentOrderRepository);
    }

    @Test
    @DisplayName("list：wire page=1 转仓储 0-based 第 0 页，回显 1-based 页码 1")
    void given_firstPage_when_list_then_repositoryGetsZeroBasedAndEchoesOneBased() {
        PaymentOrderQuery query = new PaymentOrderQuery(
            "PAY20260811", "BIZ001", "course-system",
            List.of(PaymentStatus.PAID, PaymentStatus.PENDING),
            null, null, null,
            100L, 10000L,
            LocalDateTime.of(2026, 8, 1, 0, 0), LocalDateTime.of(2026, 8, 11, 0, 0),
            null, null
        );
        Pagination pagination = new Pagination(1, 20, List.of());

        PaymentOrder order = new PaymentOrder(
            "BIZ001", "course-system", "课程购买",
            10000L, "Python 课程", "描述",
            "https://biz.example.com/notify", "attach", 3600L
        );
        order.setPayMode(PayMode.WECHAT);
        order.setAccessType(AccessType.H5);
        when(paymentOrderRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenAnswer(invocation -> new PageImpl<>(List.of(order), invocation.getArgument(1), 1));

        var result = service.list(query, pagination);

        Pageable captured = capturedPageable();
        assertThat(captured.getPageNumber()).isZero();   // wire 1-based → Spring 0-based（换算变异点）
        assertThat(captured.getPageSize()).isEqualTo(20);

        assertThat(result.items()).hasSize(1);
        assertThat(result.total()).isEqualTo(1L);
        assertThat(result.page()).isEqualTo(1);          // 回显 1-based（+1 收在 PageResponse.of）
        assertThat(result.size()).isEqualTo(20);

        PaymentOrderResponse row = result.items().get(0);
        assertThat(row.status()).isEqualTo(PaymentStatus.PENDING.getCode());
        assertThat(row.statusName()).isEqualTo(PaymentStatus.PENDING.getName());
        assertThat(row.payMode()).isEqualTo(PayMode.WECHAT.getCode());
        assertThat(row.payModeName()).isEqualTo("微信");
        assertThat(row.accessTypeName()).isEqualTo("H5");
    }

    @Test
    @DisplayName("list：空查询条件 + wire page=3，转 0-based 第 2 页，超尾页原样回显 3")
    void given_emptyQueryAndThirdPage_when_list_then_zeroBasedSecondPageAndEchoesThree() {
        PaymentOrderQuery query = new PaymentOrderQuery(
            null, null, null, null, null, null, null,
            null, null, null, null, null, null
        );
        Pagination pagination = new Pagination(3, 10, List.of());

        when(paymentOrderRepository.findAll(any(Specification.class), any(Pageable.class)))
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

    @Test
    @DisplayName("list：wire 越界值（page=0、size=500）按平台口径贴边 clamp 后再转 0-based")
    void given_outOfRangePageAndSize_when_list_then_clampedBeforeConversion() {
        PaymentOrderQuery query = new PaymentOrderQuery(
            null, null, null, null, null, null, null,
            null, null, null, null, null, null
        );
        Pagination pagination = new Pagination(0, 500, null);

        when(paymentOrderRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenAnswer(invocation -> new PageImpl<>(List.of(), invocation.getArgument(1), 0));

        var result = service.list(query, pagination);

        Pageable captured = capturedPageable();
        assertThat(captured.getPageNumber()).isZero();   // page 0 → clamp 1 → 0-based 第 0 页
        assertThat(captured.getPageSize()).isEqualTo(100); // size 500 → clamp 100

        assertThat(result.page()).isEqualTo(1);
        assertThat(result.size()).isEqualTo(100);
    }

    private Pageable capturedPageable() {
        ArgumentCaptor<Pageable> captor = ArgumentCaptor.forClass(Pageable.class);
        verify(paymentOrderRepository).findAll(any(Specification.class), captor.capture());
        return captor.getValue();
    }
}
