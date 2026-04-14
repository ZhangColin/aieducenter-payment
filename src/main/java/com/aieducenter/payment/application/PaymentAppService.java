package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.command.CreatePaymentCommand;
import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.application.mapper.PaymentOrderMapper;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.port.PaymentGatewayPort;
import com.aieducenter.payment.domain.port.response.CreatePaymentResponse;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.aieducenter.payment.infrastructure.BusinessSystemNotifier;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 支付应用服务
 *
 * <p>负责支付相关的业务编排</p>
 */
@Service
@RequiredArgsConstructor
public class PaymentAppService {

    private static final Logger log = LoggerFactory.getLogger(PaymentAppService.class);

    private final PaymentOrderRepository paymentOrderRepository;
    private final PaymentGatewayPort paymentGatewayPort;
    private final PaymentLogRepository paymentLogRepository;
    private final BusinessSystemNotifier businessSystemNotifier;

    @Value("${payment.default-expired-seconds:3600}")
    private Long defaultExpiredSeconds;

    /**
     * 创建支付订单
     *
     * @param command             创建支付命令
     * @param businessSystemName  业务系统名称（从 API Key 获取）
     * @param clientIp            客户端 IP（从 HTTP 请求获取）
     * @return 支付订单响应
     */
    @Transactional
    public PaymentOrderResponse createPayment(CreatePaymentCommand command, String businessSystemName, String clientIp) {
        Long expiredSeconds = command.expiredSeconds() != null ? command.expiredSeconds() : defaultExpiredSeconds;

        // 1. 创建支付订单
        PaymentOrder paymentOrder = new PaymentOrder(
            command.businessOrderNo(),
            businessSystemName,
            command.businessName(),
            command.amount(),
            command.subject(),
            command.body(),
            command.notifyUrl(),
            command.attach(),
            expiredSeconds
        );

        // 自动设置客户端 IP
        paymentOrder.setClientIp(clientIp);

        // 2. 检查业务订单号是否已存在
        if (paymentOrderRepository.existsByPaymentOrderNo(paymentOrder.getPaymentOrderNo())) {
            throw new IllegalArgumentException("支付订单号已存在");
        }

        // 3. 保存支付订单
        PaymentOrder saved = paymentOrderRepository.save(paymentOrder);

        // 4. 调用银行网关创建支付
        CreatePaymentResponse gatewayResponse = paymentGatewayPort.createPayment(paymentOrder);

        // 5. 记录调用日志
        PaymentLog logEntry = new PaymentLog(
            paymentOrder.getPaymentOrderNo(),
            null, // refundOrderNo
            "PAYMENT_REQUEST",
            "ICBC",
            "qrcode/consumption",
            null, // requestUrl - not needed for logging
            gatewayResponse.requestParams(),
            gatewayResponse.responseBody(),
            200, // httpStatus
            gatewayResponse.returnCode(),
            gatewayResponse.returnMsg(),
            gatewayResponse.executionTime(),
            gatewayResponse.success(),
            gatewayResponse.success() ? null : "银行调用失败"
        );
        paymentLogRepository.save(logEntry);

        // 6. 银行调用失败，抛异常让事务回滚（不留下无用的 PENDING 订单）
        if (!gatewayResponse.success()) {
            throw new RuntimeException("银行网关调用失败: " + gatewayResponse.returnMsg());
        }

        // 7. 保存 qrCodeUrl 到订单
        if (gatewayResponse.qrCodeUrl() != null) {
            paymentOrder.setQrCodeUrl(gatewayResponse.qrCodeUrl());
            paymentOrderRepository.save(paymentOrder);
        }

        // 8. 返回响应
        return PaymentOrderMapper.convert(paymentOrder);
    }

    /**
     * 查询支付订单
     *
     * @param paymentOrderNo 支付订单号
     * @return 支付订单响应
     */
    @Transactional(readOnly = true)
    public PaymentOrderResponse getPayment(String paymentOrderNo) {
        PaymentOrder paymentOrder = paymentOrderRepository.findByPaymentOrderNo(paymentOrderNo)
            .orElseThrow(() -> new IllegalArgumentException("支付订单不存在"));

        // 如果订单已过期，标记为过期状态
        if (paymentOrder.isExpired() && paymentOrder.getStatus().equals(PaymentStatus.PENDING)) {
            paymentOrder.markAsExpired();
            paymentOrderRepository.save(paymentOrder);
        }

        return PaymentOrderMapper.convert(paymentOrder);
    }

    /**
     * 主动查询支付状态
     *
     * @param paymentOrderNo 支付订单号
     * @return 支付订单响应
     */
    @Transactional
    public PaymentOrderResponse queryPaymentStatus(String paymentOrderNo) {
        PaymentOrder paymentOrder = paymentOrderRepository.findByPaymentOrderNo(paymentOrderNo)
            .orElseThrow(() -> new IllegalArgumentException("支付订单不存在"));

        // 调用银行网关查询支付状态
        var queryResponse = paymentGatewayPort.queryPayment(
            paymentOrderNo,
            paymentOrder.getBankOrderNo()
        );

        // 记录查询日志
        PaymentLog logEntry = new PaymentLog(
            paymentOrderNo,
            null,
            "PAYMENT_QUERY",
            "ICBC",
            "aggregatepay/b2c/online/orderqry",
            null,
            null,
            com.alibaba.fastjson2.JSON.toJSONString(queryResponse),
            200,
            queryResponse.returnCode(),
            queryResponse.returnMsg(),
            queryResponse.executionTime(),
            queryResponse.success(),
            null
        );
        paymentLogRepository.save(logEntry);

        // 如果查询成功且支付状态变化，更新支付订单
        if (queryResponse.success() && queryResponse.paymentStatus() != null) {
            if (queryResponse.paymentStatus().equals(PaymentStatus.PAID) &&
                paymentOrder.getStatus().equals(PaymentStatus.PENDING)) {

                paymentOrder.markAsPaid(
                    queryResponse.bankOrderNo(),
                    queryResponse.thirdPartyOrderNo(),
                    queryResponse.paymentChannel(),
                    queryResponse.paidAmount()
                );
                paymentOrderRepository.save(paymentOrder);
            } else if (queryResponse.paymentStatus().equals(PaymentStatus.FAILED) &&
                       paymentOrder.getStatus().equals(PaymentStatus.PENDING)) {

                paymentOrder.markAsFailed("银行查询返回支付失败");
                paymentOrderRepository.save(paymentOrder);
            }
        }

        return PaymentOrderMapper.convert(paymentOrder);
    }

    /**
     * 取消支付订单
     *
     * @param paymentOrderNo 支付订单号
     * @return 支付订单响应
     */
    @Transactional
    public PaymentOrderResponse cancelPayment(String paymentOrderNo) {
        PaymentOrder paymentOrder = paymentOrderRepository.findByPaymentOrderNo(paymentOrderNo)
            .orElseThrow(() -> new IllegalArgumentException("支付订单不存在"));

        paymentOrder.cancel();
        paymentOrderRepository.save(paymentOrder);

        return PaymentOrderMapper.convert(paymentOrder);
    }

    /**
     * 处理过期订单（定时任务调用）
     * <p>查询银行确认状态后再变更</p>
     */
    @Transactional
    public void processExpiredOrders() {
        List<PaymentOrder> expiredOrders = paymentOrderRepository
            .findByStatusAndExpiredAtBefore(PaymentStatus.PENDING, java.time.LocalDateTime.now());

        for (PaymentOrder order : expiredOrders) {
            try {
                // 先查银行确认状态
                var queryResponse = paymentGatewayPort.queryPayment(
                    order.getPaymentOrderNo(),
                    order.getBankOrderNo()
                );

                if (queryResponse.success() && queryResponse.paymentStatus() == PaymentStatus.PAID) {
                    // 银行已支付 → markAsPaid + 通知业务系统
                    order.markAsPaid(
                        queryResponse.bankOrderNo(),
                        queryResponse.thirdPartyOrderNo(),
                        queryResponse.paymentChannel(),
                        queryResponse.paidAmount()
                    );
                    paymentOrderRepository.save(order);

                    notifyBusinessSystem(order);
                    log.info("Expired order {} was actually paid, marked as PAID", order.getPaymentOrderNo());
                } else {
                    // 银行未支付/其他 → markAsExpired
                    order.markAsExpired();
                    paymentOrderRepository.save(order);
                    log.info("Expired order {} marked as EXPIRED", order.getPaymentOrderNo());
                }
            } catch (Exception e) {
                log.error("Failed to process expired order {}: {}", order.getPaymentOrderNo(), e.getMessage());
            }
        }
    }

    private void notifyBusinessSystem(PaymentOrder order) {
        if (order.getNotifyUrl() == null || order.getNotifyUrl().isBlank()) {
            return;
        }

        businessSystemNotifier.notify(order.getNotifyUrl(), PaymentOrderMapper.convert(order));
    }
}
