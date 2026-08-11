package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.query.RefundOrderQuery;
import com.aieducenter.payment.application.dto.response.RefundOrderResponse;
import com.aieducenter.payment.application.mapper.RefundOrderMapper;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.repository.RefundOrderRepository;
import com.cartisan.data.jpa.specification.ConditionSpecifications;
import com.cartisan.web.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 退款订单查询应用服务（读侧）。
 *
 * <p>spec「按职责拆分应用服务」：与既有写侧 {@code RefundAppService}（创建 / 审核 / 查状态同步）分离，
 * 仅承担多条件分页读列表。详情沿用既有 {@code RefundAppService.queryRefund}。查询银行无关
 * （CONTEXT.md 不变式 5）。沿用 T2（支付订单列表）确立的本仓列表查询范式。</p>
 */
@Service
@RequiredArgsConstructor
public class RefundOrderQueryAppService {

    private final RefundOrderRepository refundOrderRepository;
    private final RefundOrderMapper refundOrderMapper;

    /**
     * 多条件分页查询退款订单。
     *
     * @param query    查询条件（null / 空字符串字段自动跳过）
     * @param pageable 分页参数
     * @return 分页响应（仅暴露 DTO）
     */
    @Transactional(readOnly = true)
    public PageResponse<RefundOrderResponse> list(RefundOrderQuery query, Pageable pageable) {
        Page<RefundOrder> page = refundOrderRepository.findAll(
            ConditionSpecifications.fromAnnotation(query), pageable
        );
        return new PageResponse<>(
            refundOrderMapper.convertList(page.getContent()),
            page.getTotalElements(),
            pageable.getPageNumber() + 1,
            pageable.getPageSize()
        );
    }
}
