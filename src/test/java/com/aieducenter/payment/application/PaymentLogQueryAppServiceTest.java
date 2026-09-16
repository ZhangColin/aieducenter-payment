package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.PaymentLogQuery;
import com.aieducenter.payment.application.dto.response.PaymentLogResponse;
import com.aieducenter.payment.application.mapper.PaymentLogMapper;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
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
 * PaymentLogQueryAppService 测试（AppService Mockito 缝）。
 *
 * <p>沿用 PaymentOrderQueryAppServiceTest 模式：mock 仓储与 Mapper（DomainMapper 实例 convert），
 * 构造被测服务直接驱动。覆盖：Pagination（wire 1-based）→ Spring 0-based PageRequest 换算
 * 与 PageResponse.of 回显。查询银行无关（CONTEXT.md 不变式 5）——筛选维度为网关交互日志的
 * 通用列，无 ICBC 硬编码。</p>
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
    @DisplayName("list：多条件查询分页（logType 多值 / bankInterface / success / returnCode），wire page=1 转 0-based，回显 1-based")
    void given_queryAndFirstPage_when_list_then_zeroBasedConversionAndOneBasedEcho() {
        PaymentLogQuery query = new PaymentLogQuery(
            "PAY20260811", "REF20260811",
            List.of("PAYMENT_REQUEST", "PAYMENT_QUERY"),
            "qrcode/consumption", Boolean.TRUE, "0",
            LocalDateTime.of(2026, 8, 1, 0, 0), LocalDateTime.of(2026, 8, 11, 0, 0)
        );
        Pagination pagination = new Pagination(1, 20, List.of());

        PaymentLog log = new PaymentLog(
            "PAY20260811", "REF20260811", "PAYMENT_REQUEST", "ICBC", "qrcode/consumption",
            "https://gw/icbc/qrcode", "{\"msg\":\"req\"}", "{\"msg\":\"rsp\"}",
            200, "0", "成功", 350L, Boolean.TRUE, null
        );
        PaymentLogResponse responseDto = new PaymentLogResponse(
            1L, "PAY20260811", "REF20260811", "PAYMENT_REQUEST", "ICBC", "qrcode/consumption",
            200, "0", "成功", 350L, Boolean.TRUE, null, LocalDateTime.now()
        );
        when(paymentLogRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenAnswer(invocation -> new PageImpl<>(List.of(log), invocation.getArgument(1), 1));
        when(paymentLogMapper.convert(log)).thenReturn(responseDto);

        var result = service.list(query, pagination);

        Pageable captured = capturedPageable();
        assertThat(captured.getPageNumber()).isZero();   // wire 1-based → Spring 0-based（换算变异点）
        assertThat(captured.getPageSize()).isEqualTo(20);

        assertThat(result.items()).containsExactly(responseDto);
        assertThat(result.total()).isEqualTo(1L);
        assertThat(result.page()).isEqualTo(1);          // 回显 1-based（+1 收在 PageResponse.of）
        assertThat(result.size()).isEqualTo(20);
    }

    @Test
    @DisplayName("list：空查询条件 + wire page=3，转 0-based 第 2 页，超尾页原样回显 3")
    void given_emptyQueryAndThirdPage_when_list_then_zeroBasedSecondPageAndEchoesThree() {
        PaymentLogQuery query = new PaymentLogQuery(
            null, null, null, null, null, null, null, null
        );
        Pagination pagination = new Pagination(3, 10, List.of());

        when(paymentLogRepository.findAll(any(Specification.class), any(Pageable.class)))
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
        verify(paymentLogRepository).findAll(any(Specification.class), captor.capture());
        return captor.getValue();
    }
}
