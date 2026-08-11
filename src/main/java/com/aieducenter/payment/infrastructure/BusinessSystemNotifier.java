package com.aieducenter.payment.infrastructure;

import com.aieducenter.payment.application.dto.callback.RefundNotifyRequest;
import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.domain.enums.NotificationDeliveryResult;
import com.alibaba.fastjson2.JSON;
import com.cartisan.core.stereotype.Adapter;
import com.cartisan.core.stereotype.PortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * 业务系统通知器
 *
 * <p>支付/退款结果异步通知业务系统。POST JSON 到业务系统提供的 notifyUrl。</p>
 * <p>不重试。业务系统可通过查询接口主动获取结果。</p>
 */
@Component
@Adapter(PortType.CLIENT)
public class BusinessSystemNotifier {

    private static final Logger log = LoggerFactory.getLogger(BusinessSystemNotifier.class);

    private final HttpClient httpClient;

    public BusinessSystemNotifier() {
        this.httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();
    }

    /**
     * 通知业务系统（支付结果）。
     *
     * @return 投递结果（{@link NotificationDeliveryResult}）：blank notifyUrl → {@code SKIPPED}；
     *         HTTP 200 → {@code DELIVERED}；非 200 / 异常 → {@code FAILED}。best-effort，永不抛异常。
     */
    public NotificationDeliveryResult notify(String notifyUrl, PaymentOrderResponse response) {
        if (notifyUrl == null || notifyUrl.isBlank()) {
            log.debug("notifyUrl is empty, skip notification. orderId={}", response.paymentOrderNo());
            return NotificationDeliveryResult.SKIPPED;
        }
        return sendNotification(notifyUrl, JSON.toJSONString(response), response.paymentOrderNo());
    }

    /**
     * 通知业务系统（退款结果）。
     *
     * @return 投递结果（{@link NotificationDeliveryResult}）：blank notifyUrl → {@code SKIPPED}；
     *         HTTP 200 → {@code DELIVERED}；非 200 / 异常 → {@code FAILED}。best-effort，永不抛异常。
     */
    public NotificationDeliveryResult notify(String notifyUrl, RefundNotifyRequest request) {
        if (notifyUrl == null || notifyUrl.isBlank()) {
            log.debug("notifyUrl is empty, skip refund notification. refundOrderNo={}", request.refundOrderNo());
            return NotificationDeliveryResult.SKIPPED;
        }
        return sendNotification(notifyUrl, JSON.toJSONString(request), request.refundOrderNo());
    }

    private NotificationDeliveryResult sendNotification(String notifyUrl, String body, String orderId) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(notifyUrl))
                .timeout(Duration.ofSeconds(10))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

            log.info("Notifying business system: url={}, orderId={}", notifyUrl, orderId);

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                log.info("Business system notification succeeded: orderId={}, status={}", orderId, response.statusCode());
                return NotificationDeliveryResult.DELIVERED;
            }
            log.warn("Business system notification returned non-200: orderId={}, status={}, body={}",
                orderId, response.statusCode(), response.body());
            return NotificationDeliveryResult.FAILED;
        } catch (Exception e) {
            log.warn("Business system notification failed: orderId={}, url={}, error={}",
                orderId, notifyUrl, e.getMessage());
            return NotificationDeliveryResult.FAILED;
        }
    }
}
