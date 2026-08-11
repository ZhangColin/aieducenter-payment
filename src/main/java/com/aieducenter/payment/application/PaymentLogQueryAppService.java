package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.PaymentLogQuery;
import com.aieducenter.payment.application.dto.response.PaymentLogResponse;
import com.aieducenter.payment.application.mapper.PaymentLogMapper;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import com.cartisan.data.jpa.specification.ConditionSpecifications;
import com.cartisan.web.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 支付网关日志查询应用服务（读侧）。
 *
 * <p>把既有按单查询（{@code PaymentLogRepository#findBy*OrderByCreatedAtDesc}）扩展为多条件分页读列表，
 * 供网关交互日志的集成调试、银行侧问题排查、网关健康度统计（CONTEXT.md · PaymentLog）。
 * 查询银行无关（CONTEXT.md 不变式 5）——筛选维度为网关交互日志的通用列，无 ICBC 硬编码。
 * 沿用 T2（支付订单列表）确立的本仓列表查询范式。</p>
 */
@Service
@RequiredArgsConstructor
public class PaymentLogQueryAppService {

    private final PaymentLogRepository paymentLogRepository;
    private final PaymentLogMapper paymentLogMapper;

    /**
     * 多条件分页查询支付网关日志。
     *
     * @param query    查询条件（null / 空字符串字段自动跳过）
     * @param pageable 分页参数
     * @return 分页响应（仅暴露 DTO）
     */
    @Transactional(readOnly = true)
    public PageResponse<PaymentLogResponse> list(PaymentLogQuery query, Pageable pageable) {
        Page<PaymentLog> page = paymentLogRepository.findAll(
            ConditionSpecifications.fromAnnotation(query), pageable
        );
        return new PageResponse<>(
            paymentLogMapper.convertList(page.getContent()),
            page.getTotalElements(),
            pageable.getPageNumber() + 1,
            pageable.getPageSize()
        );
    }
}
