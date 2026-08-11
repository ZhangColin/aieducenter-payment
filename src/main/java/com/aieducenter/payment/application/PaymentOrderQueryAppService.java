package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.PaymentOrderQuery;
import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.application.mapper.PaymentOrderMapper;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.cartisan.data.jpa.specification.ConditionSpecifications;
import com.cartisan.web.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 支付订单查询应用服务（读侧）。
 *
 * <p>spec「按职责拆分应用服务」：与既有写侧 {@code PaymentAppService}（创建 / 查状态 / 取消）分离，
 * 仅承担多条件分页读列表。详情沿用既有 {@code PaymentAppService.getPayment}。查询银行无关
 * （CONTEXT.md 不变式 5）。</p>
 */
@Service
@RequiredArgsConstructor
public class PaymentOrderQueryAppService {

    private final PaymentOrderRepository paymentOrderRepository;
    private final PaymentOrderMapper paymentOrderMapper;

    /**
     * 多条件分页查询支付订单。
     *
     * @param query    查询条件（null / 空字符串字段自动跳过）
     * @param pageable 分页参数
     * @return 分页响应（仅暴露 DTO）
     */
    @Transactional(readOnly = true)
    public PageResponse<PaymentOrderResponse> list(PaymentOrderQuery query, Pageable pageable) {
        Page<PaymentOrder> page = paymentOrderRepository.findAll(
            ConditionSpecifications.fromAnnotation(query), pageable
        );
        return new PageResponse<>(
            paymentOrderMapper.convertList(page.getContent()),
            page.getTotalElements(),
            pageable.getPageNumber() + 1,
            pageable.getPageSize()
        );
    }
}
