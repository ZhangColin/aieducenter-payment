package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.OperationLogQuery;
import com.aieducenter.payment.application.dto.response.OperationLogResponse;
import com.aieducenter.payment.application.mapper.OperationLogMapper;
import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.repository.OperationLogRepository;
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
 * OperationLogAppService 测试（AppService Mockito 缝）。
 *
 * <p>沿用 RefundAppServiceTest 模式：mock 仓储与 Mapper，构造被测服务直接驱动。
 * 用 ArgumentCaptor 验证传给仓储的聚合字段（变异友好）。</p>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("操作日志应用服务测试")
class OperationLogAppServiceTest {

    @Mock private OperationLogRepository operationLogRepository;
    @Mock private OperationLogMapper operationLogMapper;

    private OperationLogAppService service;

    @BeforeEach
    void setUp() {
        service = new OperationLogAppService(operationLogRepository, operationLogMapper);
    }

    @Test
    @DisplayName("record：落库一条操作日志，全部字段正确传递")
    void given_validOperation_when_record_then_saveOperationLogWithAllFields() {
        service.record(
            OperationLogTargetType.REFUND, "REF001", OperationType.AUDIT_APPROVE,
            123L, "张三", "admin-bff", "SUCCESS", "同意退款"
        );

        ArgumentCaptor<OperationLog> captor = ArgumentCaptor.forClass(OperationLog.class);
        verify(operationLogRepository).save(captor.capture());
        OperationLog saved = captor.getValue();
        assertThat(saved.getTargetType()).isEqualTo(OperationLogTargetType.REFUND);
        assertThat(saved.getTargetNo()).isEqualTo("REF001");
        assertThat(saved.getOperation()).isEqualTo(OperationType.AUDIT_APPROVE);
        assertThat(saved.getOperatorId()).isEqualTo(123L);
        assertThat(saved.getOperatorName()).isEqualTo("张三");
        assertThat(saved.getOperatorSystem()).isEqualTo("admin-bff");
        assertThat(saved.getResult()).isEqualTo("SUCCESS");
        assertThat(saved.getRemark()).isEqualTo("同意退款");
    }

    @Test
    @DisplayName("list：按查询条件分页，wire page=1 转 0-based，回显 1-based 且仅暴露 DTO")
    void given_queryAndFirstPage_when_list_then_zeroBasedConversionAndOneBasedEcho() {
        OperationLogQuery query = new OperationLogQuery(
            OperationLogTargetType.REFUND, "REF001", OperationType.AUDIT_APPROVE,
            123L, "admin-bff", "SUCCESS", null, null
        );
        Pagination pagination = new Pagination(1, 20, List.of());

        OperationLog log = new OperationLog(
            OperationLogTargetType.REFUND, "REF001", OperationType.AUDIT_APPROVE,
            123L, "张三", "admin-bff", "SUCCESS", "同意退款"
        );
        OperationLogResponse responseDto = new OperationLogResponse(
            1L, 2, "退款订单", "REF001", 1, "审核通过",
            123L, "张三", "admin-bff", "SUCCESS", "同意退款", LocalDateTime.now()
        );
        when(operationLogRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenAnswer(invocation -> new PageImpl<>(List.of(log), invocation.getArgument(1), 1));
        when(operationLogMapper.convert(log)).thenReturn(responseDto);

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
        OperationLogQuery query = new OperationLogQuery(
            null, null, null, null, null, null, null, null
        );
        Pagination pagination = new Pagination(3, 10, List.of());

        when(operationLogRepository.findAll(any(Specification.class), any(Pageable.class)))
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
        verify(operationLogRepository).findAll(any(Specification.class), captor.capture());
        return captor.getValue();
    }
}
