package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.OperationLogQuery;
import com.aieducenter.payment.application.dto.response.OperationLogResponse;
import com.aieducenter.payment.application.mapper.OperationLogMapper;
import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.repository.OperationLogRepository;
import com.cartisan.data.jpa.specification.ConditionSpecifications;
import com.cartisan.web.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 操作日志应用服务。
 *
 * <p>供各写操作（审核、通知重发等）调用 {@link #record} 落库；提供 {@link #list} 多条件分页查询。</p>
 */
@Service
@RequiredArgsConstructor
public class OperationLogAppService {

    private final OperationLogRepository operationLogRepository;
    private final OperationLogMapper operationLogMapper;

    /**
     * 记录一条操作日志。
     *
     * @param targetType     目标类型
     * @param targetNo       目标单号
     * @param operation      操作类型
     * @param operatorId     操作者 ID（可空）
     * @param operatorName   操作者名称（可空）
     * @param operatorSystem 来源系统（可空）
     * @param result         操作结果（稳定 token）
     * @param remark         备注（可空）
     */
    @Transactional
    public void record(
            OperationLogTargetType targetType,
            String targetNo,
            OperationType operation,
            Long operatorId,
            String operatorName,
            String operatorSystem,
            String result,
            String remark
    ) {
        OperationLog logEntry = new OperationLog(
            targetType, targetNo, operation,
            operatorId, operatorName, operatorSystem,
            result, remark
        );
        operationLogRepository.save(logEntry);
    }

    /**
     * 多条件分页查询操作日志。
     */
    @Transactional(readOnly = true)
    public PageResponse<OperationLogResponse> list(OperationLogQuery query, Pageable pageable) {
        Page<OperationLog> page = operationLogRepository.findAll(
            ConditionSpecifications.fromAnnotation(query), pageable
        );
        return new PageResponse<>(
            operationLogMapper.convertList(page.getContent()),
            page.getTotalElements(),
            pageable.getPageNumber() + 1,
            pageable.getPageSize()
        );
    }
}
