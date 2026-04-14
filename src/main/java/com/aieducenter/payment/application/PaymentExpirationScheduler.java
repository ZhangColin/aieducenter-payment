package com.aieducenter.payment.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 过期订单定时任务
 *
 * <p>定时轮询超过过期时间仍为 PENDING 的订单，先查银行确认状态后处理</p>
 */
@Component
@RequiredArgsConstructor
public class PaymentExpirationScheduler {

    private static final Logger log = LoggerFactory.getLogger(PaymentExpirationScheduler.class);

    private final PaymentAppService paymentAppService;

    /**
     * 每5分钟执行一次过期订单检查
     */
    @Scheduled(cron = "${scheduler.payment-expiration:0 */5 * * * *}")
    public void expirePendingOrders() {
        log.info("Starting expired order check...");
        try {
            paymentAppService.processExpiredOrders();
            log.info("Expired order check completed.");
        } catch (Exception e) {
            log.error("Error during expired order check", e);
        }
    }
}
