package com.aieducenter.payment.hsb.application;

import com.aieducenter.payment.hsb.domain.enums.HsbRefundStatus;
import com.aieducenter.payment.hsb.domain.repository.HsbRefundOrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HsbRefundQueryScheduler {

    private static final Logger log = LoggerFactory.getLogger(HsbRefundQueryScheduler.class);

    private final HsbRefundOrderRepository refundOrderRepository;
    private final HsbRefundAppService refundAppService;

    @Scheduled(cron = "${scheduler.hsb-refund-query:0 */5 * * * *}")
    public void queryRefundingOrders() {
        log.info("HSB refund query scheduler started");

        var refundingOrders = refundOrderRepository.findByStatus(HsbRefundStatus.REFUNDING);

        for (var order : refundingOrders) {
            try {
                refundAppService.queryRefundStatus(order.getRefundOrderNo());
            } catch (Exception e) {
                log.error("HSB refund query failed for order {}: {}",
                    order.getRefundOrderNo(), e.getMessage());
            }
        }

        log.info("HSB refund query scheduler completed, processed {} orders", refundingOrders.size());
    }
}
