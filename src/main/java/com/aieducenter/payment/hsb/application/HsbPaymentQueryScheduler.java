package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.aieducenter.payment.hsb.domain.repository.HsbPaymentOrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HsbPaymentQueryScheduler {

    private static final Logger log = LoggerFactory.getLogger(HsbPaymentQueryScheduler.class);

    private final HsbPaymentOrderRepository paymentOrderRepository;
    private final HsbPaymentAppService paymentAppService;

    @Scheduled(cron = "${scheduler.hsb-payment-query:0 */5 * * * *}")
    public void queryPendingOrders() {
        log.info("HSB payment query scheduler started");

        var pendingOrders = paymentOrderRepository
            .findByStatusAndExpiredAtBefore(HsbPaymentStatus.PENDING, java.time.LocalDateTime.now());

        for (var order : pendingOrders) {
            try {
                paymentAppService.queryPaymentStatus(order.getPaymentOrderNo());
                // 查询后如果仍为 PENDING，说明银行也没有支付结果，直接标记为超时
                if (order.getStatus() == HsbPaymentStatus.PENDING) {
                    log.info("HSB payment order {} expired, marking as expired", order.getPaymentOrderNo());
                    order.markAsExpired();
                    paymentOrderRepository.save(order);
                }
            } catch (Exception e) {
                log.error("HSB payment query failed for order {}: {}",
                    order.getPaymentOrderNo(), e.getMessage());
            }
        }

        log.info("HSB payment query scheduler completed, processed {} orders", pendingOrders.size());
    }
}
