package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.PaymentLogQuery;
import com.aieducenter.payment.application.dto.response.PaymentLogResponse;
import com.aieducenter.payment.application.mapper.PaymentLogMapper;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
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
 * PaymentLogQueryAppService 测试（AppService Mockito 缝）。
 *
 * <p>沿用 RefundOrderQueryAppServiceTest / PaymentOrderQueryAppServiceTest 模式：mock 仓储与 Mapper，
 * 构造被测服务直接驱动。覆盖：查询参数正确传递（Specification + Pageable）与 PageResponse 响应映射
 * （变异友好）。查询银行无关（CONTEXT.md 不变式 5）——筛选维度为网关交互日志的通用列，无 ICBC 硬编码。</p>
 *
 * <p>说明：仓储 findAll 被 mock，Specification 不真正执行（不引入 @DataJpaTest，与现有代码库一致）；
 * 查询正确性靠 @Condition 注解的编译期保证与显式可读性。</p>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("支付网关日志查询应用服务测试")
class PaymentLogQueryAppServiceTest {

    @Mock private PaymentLogRepository paymentLogRepository;
    @Mock private PaymentLogMapper paymentLogMapper;

    private PaymentLogQueryAppService service;

    @BeforeEach
    void setUp() {
        service = new PaymentLogQueryAppService(paymentLogRepository, paymentLogMapper);
    }

    @Test
    @DisplayName("list：多条件查询分页（logType 多值 / bankInterface / success / returnCode），返回 PageResponse 且仅暴露 DTO，page 为 1-based")
    void given_queryAndPageable_when_list_then_returnsPageResponseOfDtos() {
        PaymentLogQuery query = new PaymentLogQuery(
            "PAY20260811", "REF20260811",
            List.of("PAYMENT_REQUEST", "PAYMENT_QUERY"),
            "qrcode/consumption", Boolean.TRUE, "0",
            LocalDateTime.of(2026, 8, 1, 0, 0), LocalDateTime.of(2026, 8, 11, 0, 0)
        );
        Pageable pageable = PageRequest.of(0, 20);

        PaymentLog log = new PaymentLog(
            "PAY20260811", "REF20260811", "PAYMENT_REQUEST", "ICBC", "qrcode/consumption",
            "https://gw/icbc/qrcode", "{\"msg\":\"req\"}", "{\"msg\":\"rsp\"}",
            200, "0", "成功", 350L, Boolean.TRUE, null
        );
        Page<PaymentLog> page = new PageImpl<>(List.of(log), pageable, 1);
        when(paymentLogRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(page);

        PaymentLogResponse responseDto = new PaymentLogResponse(
            1L, "PAY20260811", "REF20260811", "PAYMENT_REQUEST", "ICBC", "qrcode/consumption",
            200, "0", "成功", 350L, Boolean.TRUE, null, LocalDateTime.now()
        );
        when(paymentLogMapper.convertList(List.of(log))).thenReturn(List.of(responseDto));

        var result = service.list(query, pageable);

        assertThat(result.items()).containsExactly(responseDto);
        assertThat(result.total()).isEqualTo(1L);
        assertThat(result.page()).isEqualTo(1);          // 0-based → 1-based（+1 变异点）
        assertThat(result.size()).isEqualTo(20);
        verify(paymentLogRepository).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    @DisplayName("list：空查询条件也返回 PageResponse（Specification 不抛错）")
    void given_emptyQuery_when_list_then_returnsPageResponse() {
        PaymentLogQuery query = new PaymentLogQuery(
            null, null, null, null, null, null, null, null
        );
        Pageable pageable = PageRequest.of(2, 10);

        Page<PaymentLog> emptyPage = new PageImpl<>(List.of(), pageable, 0);
        when(paymentLogRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(emptyPage);
        when(paymentLogMapper.convertList(List.of())).thenReturn(List.of());

        var result = service.list(query, pageable);

        assertThat(result.items()).isEmpty();
        assertThat(result.total()).isZero();
        assertThat(result.page()).isEqualTo(3);          // PageRequest.of(2,10) → page 3
        assertThat(result.size()).isEqualTo(10);
    }
}
