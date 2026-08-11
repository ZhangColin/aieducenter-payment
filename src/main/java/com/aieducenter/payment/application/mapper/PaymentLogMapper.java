package com.aieducenter.payment.application.mapper;

import com.aieducenter.payment.application.dto.response.PaymentLogResponse;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.cartisan.web.mapper.DomainMapper;
import org.mapstruct.Mapper;

/**
 * 支付网关日志映射器。
 *
 * <p>继承 {@link DomainMapper} 获得 {@code convertList}；PaymentLog 可暴露字段均为基础类型，
 * 与 {@link PaymentLogResponse} 同名同型，MapStruct 自动映射，无需显式 {@code @Mapping}
 * （区别于 OperationLogMapper / RefundOrderMapper 的枚举拆分）。</p>
 */
@Mapper(componentModel = "spring")
public interface PaymentLogMapper extends DomainMapper<PaymentLog, PaymentLogResponse> {

    @Override
    PaymentLogResponse convert(PaymentLog paymentLog);
}
