package com.aieducenter.payment.domain.repository;

import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.cartisan.data.jpa.repository.BaseRepository;

import java.util.List;

/**
 * 支付日志仓储接口
 *
 * <p>领域层定义，基础设施层提供实现</p>
 */
public interface PaymentLogRepository extends BaseRepository<PaymentLog, Long> {
    /**
     * 根据支付订单号查询日志，按创建时间倒序
     *
     * @param paymentOrderNo 支付订单号
     * @return 日志列表
     */
    List<PaymentLog> findByPaymentOrderNoOrderByCreatedAtDesc(String paymentOrderNo);

    /**
     * 根据退款订单号查询日志，按创建时间倒序
     *
     * @param refundOrderNo 退款订单号
     * @return 日志列表
     */
    List<PaymentLog> findByRefundOrderNoOrderByCreatedAtDesc(String refundOrderNo);
}
