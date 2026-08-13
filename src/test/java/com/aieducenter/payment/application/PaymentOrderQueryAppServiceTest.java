package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.PaymentOrderQuery;
import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.application.mapper.PaymentOrderMapper;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PayMode;
import com.aieducenter.payment.domain.enums.PaymentChannel;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
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
 * PaymentOrderQueryAppService 测试（AppService Mockito 缝）。
 *
 * <p>沿用 OperationLogAppServiceTest 模式：mock 仓储与 Mapper，构造被测服务直接驱动。
 * 覆盖：查询参数正确传递（Specification + Pageable）与 PageResponse 响应映射（变异友好）。</p>
 *
 * <p>说明：仓储 findAll 被 mock，Specification 不真正执行（不引入 @DataJpaTest，
 * 与现有代码库一致）；查询正确性靠 @Condition 注解的编译期保证与显式可读性。</p>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("支付订单查询应用服务测试")
class PaymentOrderQueryAppServiceTest {

    @Mock private PaymentOrderRepository paymentOrderRepository;
    @Mock private PaymentOrderMapper paymentOrderMapper;

    private PaymentOrderQueryAppService service;

    @BeforeEach
    void setUp() {
        service = new PaymentOrderQueryAppService(paymentOrderRepository, paymentOrderMapper);
    }

    @Test
    @DisplayName("list：多条件查询分页，返回 PageResponse 且仅暴露 DTO，page 为 1-based")
    void given_queryAndPageable_when_list_then_returnsPageResponseOfDtos() {
        PaymentOrderQuery query = new PaymentOrderQuery(
            "PAY20260811", "BIZ001", "course-system",
            List.of(PaymentStatus.PAID, PaymentStatus.PENDING),
            null, null, null,
            100L, 10000L,
            LocalDateTime.of(2026, 8, 1, 0, 0), LocalDateTime.of(2026, 8, 11, 0, 0),
            null, null
        );
        Pageable pageable = PageRequest.of(0, 20);

        PaymentOrder order = new PaymentOrder(
            "BIZ001", "course-system", "课程购买",
            10000L, "Python 课程", "描述",
            "https://biz.example.com/notify", "attach", 3600L
        );
        Page<PaymentOrder> page = new PageImpl<>(List.of(order), pageable, 1);
        when(paymentOrderRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(page);

        PaymentOrderResponse responseDto = new PaymentOrderResponse(
            1L, "BIZ001", "PAY20260811", "course-system", "课程购买",
            PaymentStatus.PAID.getCode(), PaymentStatus.PAID.getName(),
            PayMode.WECHAT.getCode(), PayMode.WECHAT.getName(),
            AccessType.H5.getCode(), AccessType.H5.getName(),
            10000L, "Python 课程", "描述",
            PaymentChannel.ICBC.getCode(), "工商银行",
            null, "127.0.0.1", LocalDateTime.now(), null, LocalDateTime.now(),
            "ICBC_ORDER_001", "THIRD_001"
        );
        when(paymentOrderMapper.convertList(List.of(order))).thenReturn(List.of(responseDto));

        var result = service.list(query, pageable);

        assertThat(result.items()).containsExactly(responseDto);
        assertThat(result.total()).isEqualTo(1L);
        assertThat(result.page()).isEqualTo(1);          // 0-based → 1-based（+1 变异点）
        assertThat(result.size()).isEqualTo(20);
        assertThat(responseDto.payMode()).isEqualTo(PayMode.WECHAT.getCode());
        assertThat(responseDto.payModeName()).isEqualTo("微信");
        assertThat(responseDto.accessTypeName()).isEqualTo("H5");
        assertThat(responseDto.paymentChannel()).isEqualTo(PaymentChannel.ICBC.getCode());
        verify(paymentOrderRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    @DisplayName("list：空查询条件也返回 PageResponse（Specification 不抛错）")
    void given_emptyQuery_when_list_then_returnsPageResponse() {
        PaymentOrderQuery query = new PaymentOrderQuery(
            null, null, null, null, null, null, null,
            null, null, null, null, null, null
        );
        Pageable pageable = PageRequest.of(2, 10);

        Page<PaymentOrder> emptyPage = new PageImpl<>(List.of(), pageable, 0);
        when(paymentOrderRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(emptyPage);
        when(paymentOrderMapper.convertList(List.of())).thenReturn(List.of());

        var result = service.list(query, pageable);

        assertThat(result.items()).isEmpty();
        assertThat(result.total()).isZero();
        assertThat(result.page()).isEqualTo(3);          // PageRequest.of(2,10) → page 3
        assertThat(result.size()).isEqualTo(10);
    }
}
