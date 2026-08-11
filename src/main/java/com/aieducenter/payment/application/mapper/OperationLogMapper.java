package com.aieducenter.payment.application.mapper;

import com.aieducenter.payment.application.dto.response.OperationLogResponse;
import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.cartisan.web.mapper.DomainMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 操作日志映射器。
 *
 * <p>继承 {@link DomainMapper} 获得 {@code convertList}；枚举 → Integer code / String name 显式映射。</p>
 */
@Mapper(componentModel = "spring")
public interface OperationLogMapper extends DomainMapper<OperationLog, OperationLogResponse> {

    @Mapping(target = "targetType", source = "targetType.code")
    @Mapping(target = "targetTypeName", source = "targetType.name")
    @Mapping(target = "operation", source = "operation.code")
    @Mapping(target = "operationName", source = "operation.name")
    @Override
    OperationLogResponse convert(OperationLog operationLog);
}
