package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.OperationLogQuery;
import com.aieducenter.payment.application.dto.response.OperationLogResponse;
import com.aieducenter.payment.application.mapper.OperationLogMapper;
import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.repository.OperationLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
    @DisplayName("list：按查询条件分页，返回 PageResponse 且仅暴露 DTO")
    void given_queryAndPageable_when_list_then_returnsPageResponseOfDtos() {
        OperationLogQuery query = new OperationLogQuery(
            OperationLogTargetType.REFUND, "REF001", OperationType.AUDIT_APPROVE,
            123L, "admin-bff", "SUCCESS", null, null
        );
        Pageable pageable = PageRequest.of(0, 20);

        OperationLog log = new OperationLog(
            OperationLogTargetType.REFUND, "REF001", OperationType.AUDIT_APPROVE,
            123L, "张三", "admin-bff", "SUCCESS", "同意退款"
        );
        Page<OperationLog> page = new PageImpl<>(List.of(log), pageable, 1);
        when(operationLogRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(page);

        OperationLogResponse responseDto = new OperationLogResponse(
            1L, 2, "退款订单", "REF001", 1, "审核通过",
            123L, "张三", "admin-bff", "SUCCESS", "同意退款", LocalDateTime.now()
        );
        when(operationLogMapper.convertList(List.of(log))).thenReturn(List.of(responseDto));

        var result = service.list(query, pageable);

        assertThat(result.items()).containsExactly(responseDto);
        assertThat(result.total()).isEqualTo(1L);
        assertThat(result.page()).isEqualTo(1);
        assertThat(result.size()).isEqualTo(20);
    }

    @Test
    @DisplayName("list：空查询条件也返回 PageResponse（Specification 不抛错）")
    void given_emptyQuery_when_list_then_returnsPageResponse() {
        OperationLogQuery query = new OperationLogQuery(
            null, null, null, null, null, null, null, null
        );
        Pageable pageable = PageRequest.of(0, 10);

        Page<OperationLog> emptyPage = new PageImpl<>(List.of(), pageable, 0);
        when(operationLogRepository.findAll(any(Specification.class), any(Pageable.class)))
            .thenReturn(emptyPage);
        when(operationLogMapper.convertList(List.of())).thenReturn(List.of());

        var result = service.list(query, pageable);

        assertThat(result.items()).isEmpty();
        assertThat(result.total()).isZero();
    }
}
