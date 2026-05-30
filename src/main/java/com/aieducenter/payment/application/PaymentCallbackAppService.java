package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.callback.IcbcCallbackParam;
import com.aieducenter.payment.application.mapper.PaymentOrderMapper;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PayMode;
import com.aieducenter.payment.domain.enums.PaymentChannel;
import com.aieducenter.payment.domain.enums.PaymentStatus;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.aieducenter.payment.infrastructure.BusinessSystemNotifier;
import com.aieducenter.payment.infrastructure.icbc.IcbcCallbackVerifier;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 支付回调应用服务
 *
 * <p>处理工行支付结果回调，编排：验签 → 更新订单 → 记录日志 → 通知业务系统 → 返回应答</p>
 */
@Service
@RequiredArgsConstructor
public class PaymentCallbackAppService {

    private static final Logger log = LoggerFactory.getLogger(PaymentCallbackAppService.class);
    private static final DateTimeFormatter ICBC_PAY_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final DateTimeFormatter DISPLAY_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final PaymentOrderRepository paymentOrderRepository;
    private final PaymentLogRepository paymentLogRepository;
    private final IcbcCallbackVerifier icbcCallbackVerifier;
    private final BusinessSystemNotifier businessSystemNotifier;

    /**
     * 处理工行回调
     */
    @Transactional
    public String handleCallback(String path, Map<String, String> params, String sign, IcbcCallbackParam callback) {
        log.info("Received ICBC callback: outTradeNo={}, returnCode={}", callback.outTradeNo(), callback.returnCode());

        // 1. 验签
        if (!icbcCallbackVerifier.verifySignature(path, params, sign)) {
            log.warn("ICBC callback signature verification failed: outTradeNo={}", callback.outTradeNo());
            return buildErrorResponse();
        }

        // 2. 查找订单
        PaymentOrder order = paymentOrderRepository.findByPaymentOrderNo(callback.outTradeNo()).orElse(null);
        if (order == null) {
            log.warn("ICBC callback: order not found, outTradeNo={}", callback.outTradeNo());
            return icbcCallbackVerifier.buildCallbackResponse(icbcCallbackVerifier.generateResponseMsgId());
        }

        // 3. 记录日志（无论是否成功都记录）
        saveCallbackLog(order.getPaymentOrderNo(), callback);

        // 4. 幂等检查：只有 PENDING 状态才处理
        if (order.getStatus() != PaymentStatus.PENDING) {
            log.info("ICBC callback: order already in terminal state, orderId={}, status={}",
                order.getPaymentOrderNo(), order.getStatus());
            return icbcCallbackVerifier.buildCallbackResponse(icbcCallbackVerifier.generateResponseMsgId());
        }

        // 5. 更新订单状态
        if (callback.isPaymentSuccess()) {
            Long actualAmount = parseAmount(callback.paymentAmt());
            PaymentChannel paymentChannel = mapPaymentChannel(callback.payType());
            order.markAsPaid(callback.orderId(), callback.thirdTradeNo(), paymentChannel, actualAmount);

            // 回填 payMode 和 accessType
            if (order.getPayMode() == null) {
                PayMode payMode = mapPayMode(callback.payType());
                if (payMode != null) {
                    order.setPayMode(payMode);
                }
            }
            if (order.getAccessType() == null) {
                AccessType accessType = mapAccessType(callback.accessType());
                if (accessType != null) {
                    order.setAccessType(accessType);
                }
            }
        } else {
            order.markAsFailed(callback.returnMsg());
        }
        paymentOrderRepository.save(order);

        // 6. 通知业务系统（在事务提交后执行，避免在事务中阻塞HTTP调用）
        PaymentOrder savedOrder = order;
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                notifyBusinessSystem(savedOrder);
            }
        });

        // 7. 返回工行应答
        return icbcCallbackVerifier.buildCallbackResponse(icbcCallbackVerifier.generateResponseMsgId());
    }

    private void saveCallbackLog(String paymentOrderNo, IcbcCallbackParam callback) {
        PaymentLog logEntry = new PaymentLog(
            paymentOrderNo,
            null,
            "PAYMENT_CALLBACK",
            "ICBC",
            "qrcode/consumption/callback",
            null,
            null,
            JSON.toJSONString(callback),
            200,
            callback.returnCode(),
            callback.returnMsg(),
            null,
            callback.isPaymentSuccess(),
            null
        );
        paymentLogRepository.save(logEntry);
    }

    private void notifyBusinessSystem(PaymentOrder order) {
        businessSystemNotifier.notify(order.getNotifyUrl(), PaymentOrderMapper.convert(order));
    }

    private String formatPayTime(String icbcPayTime) {
        if (icbcPayTime == null || icbcPayTime.isEmpty()) {
            return null;
        }
        try {
            LocalDateTime time = LocalDateTime.parse(icbcPayTime, ICBC_PAY_TIME_FORMAT);
            return time.format(DISPLAY_TIME_FORMAT);
        } catch (Exception e) {
            return icbcPayTime;
        }
    }

    private Long parseAmount(String amountStr) {
        if (amountStr == null || amountStr.isEmpty()) {
            return null;
        }
        try {
            return Long.parseLong(amountStr);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private PaymentChannel mapPaymentChannel(String payType) {
        // 所有通过工行网关的支付统一为 ICBC 渠道
        return PaymentChannel.ICBC;
    }

    private PayMode mapPayMode(String payType) {
        if (payType == null) return null;
        for (PayMode pm : PayMode.values()) {
            if (pm.getCode().toString().equals(payType)) {
                return pm;
            }
        }
        return null;
    }

    private AccessType mapAccessType(String accessType) {
        if (accessType == null) return null;
        for (AccessType at : AccessType.values()) {
            if (at.getCode().toString().equals(accessType)) {
                return at;
            }
        }
        return null;
    }

    private String buildErrorResponse() {
        return icbcCallbackVerifier.buildCallbackResponse(icbcCallbackVerifier.generateResponseMsgId());
    }
}
