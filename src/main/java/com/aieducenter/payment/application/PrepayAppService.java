package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.command.CreatePrepayCommand;
import com.aieducenter.payment.application.dto.response.PrepayOrderResponse;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.enums.AccessType;
import com.aieducenter.payment.domain.enums.PayMode;
import com.aieducenter.payment.domain.port.PaymentGatewayPort;
import com.aieducenter.payment.domain.port.response.CreatePrepayResponse;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 预支付应用服务
 *
 * <p>负责聚合支付（微信/支付宝/云闪付）的业务编排</p>
 */
@Service
@RequiredArgsConstructor
public class PrepayAppService {

    private static final Logger log = LoggerFactory.getLogger(PrepayAppService.class);

    private final PaymentOrderRepository paymentOrderRepository;
    private final PaymentGatewayPort paymentGatewayPort;
    private final PaymentLogRepository paymentLogRepository;

    @Value("${payment.default-expired-seconds:3600}")
    private Long defaultExpiredSeconds;

    /**
     * 创建预支付订单
     *
     * @param command             创建预支付命令
     * @param businessSystemName  业务系统名称（从 API Key 获取）
     * @param clientIp            客户端 IP（从 HTTP 请求获取）
     * @return 预支付订单响应
     */
    @Transactional
    public PrepayOrderResponse createPrepay(CreatePrepayCommand command, String businessSystemName, String clientIp) {
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

        // 设置客户端 IP
        paymentOrder.setClientIp(clientIp);

        // 设置预支付特有字段
        for (PayMode pm : PayMode.values()) {
            if (pm.getCode().equals(command.payMode())) {
                paymentOrder.setPayMode(pm);
                break;
            }
        }
        for (AccessType at : AccessType.values()) {
            if (at.getCode().equals(command.accessType())) {
                paymentOrder.setAccessType(at);
                break;
            }
        }
        paymentOrder.setOpenId(command.openId());

        // 2. 保存订单
        PaymentOrder saved = paymentOrderRepository.save(paymentOrder);

        // 3. 调用银行网关创建预支付
        CreatePrepayResponse gatewayResponse = paymentGatewayPort.createPrepay(paymentOrder);

        // 4. 记录调用日志
        PaymentLog logEntry = new PaymentLog(
            paymentOrder.getPaymentOrderNo(),
            null,
            "PREPAY_REQUEST",
            "ICBC",
            "aggregatepay/b2c/online/consumepurchase",
            null,
            gatewayResponse.requestParams(),
            gatewayResponse.responseBody(),
            200,
            gatewayResponse.returnCode(),
            gatewayResponse.returnMsg(),
            gatewayResponse.executionTime(),
            gatewayResponse.success(),
            gatewayResponse.success() ? null : "银行调用失败"
        );
        paymentLogRepository.save(logEntry);

        // 5. 银行调用失败，抛异常
        if (!gatewayResponse.success()) {
            throw new RuntimeException("银行网关调用失败: " + gatewayResponse.returnMsg());
        }

        // 6. 保存预支付参数包
        if (gatewayResponse.dataPackage() != null) {
            paymentOrder.setPrepayDataPackage(gatewayResponse.dataPackage());
        }
        if (gatewayResponse.tradeType() != null) {
            paymentOrder.setTradeType(gatewayResponse.tradeType());
        }
        paymentOrderRepository.save(paymentOrder);

        // 7. 返回响应
        return new PrepayOrderResponse(
            paymentOrder.getId(),
            paymentOrder.getBusinessOrderNo(),
            paymentOrder.getPaymentOrderNo(),
            paymentOrder.getBusinessSystemName(),
            paymentOrder.getStatus().getCode(),
            paymentOrder.getStatus().getName(),
            paymentOrder.getAmount(),
            paymentOrder.getPrepayDataPackage(),
            paymentOrder.getPayMode() != null ? paymentOrder.getPayMode().getCode() : null,
            paymentOrder.getPayMode() != null ? paymentOrder.getPayMode().getName() : null,
            paymentOrder.getAccessType() != null ? paymentOrder.getAccessType().getCode() : null,
            paymentOrder.getAccessType() != null ? paymentOrder.getAccessType().getName() : null,
            paymentOrder.getExpiredAt(),
            paymentOrder.getCreatedAt()
        );
    }
}
