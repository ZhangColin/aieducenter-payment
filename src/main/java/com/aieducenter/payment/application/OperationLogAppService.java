package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.OperationLogQuery;
import com.aieducenter.payment.application.dto.response.OperationLogResponse;
import com.aieducenter.payment.application.mapper.OperationLogMapper;
import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.aieducenter.payment.domain.enums.OperationLogTargetType;
import com.aieducenter.payment.domain.enums.OperationType;
import com.aieducenter.payment.domain.repository.OperationLogRepository;
import com.cartisan.data.jpa.specification.ConditionSpecifications;
import com.cartisan.web.request.Pagination;
import com.cartisan.web.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
     *
     * @param query      查询条件（null / 空字符串字段自动跳过）
     * @param pagination 分页参数（wire 1-based；页码换算与 clamp 收在框架 {@link Pagination}）
     * @return 分页响应（仅暴露 DTO；1-based 页码回显收在 {@link PageResponse#of}）
     */
    @Transactional(readOnly = true)
    public PageResponse<OperationLogResponse> list(OperationLogQuery query, Pagination pagination) {
        Page<OperationLog> page = operationLogRepository.findAll(
            ConditionSpecifications.fromAnnotation(query), pagination.toPageRequest()
        );
        return PageResponse.of(page.map(operationLogMapper::convert));
    }
}
