package com.aieducenter.payment.application;

import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import com.aieducenter.payment.domain.enums.RefundStatus;
import com.aieducenter.payment.domain.error.PaymentMessage;
import com.aieducenter.payment.domain.repository.PaymentOrderRepository;
import com.aieducenter.payment.domain.repository.RefundOrderRepository;
import com.cartisan.core.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RefundQueryScheduler {

    private static final Logger log = LoggerFactory.getLogger(RefundQueryScheduler.class);
    private static final int BATCH_SIZE = 100;

    private final RefundOrderRepository refundOrderRepository;
    private final PaymentOrderRepository paymentOrderRepository;
    private final RefundAppService refundAppService;

    @Scheduled(cron = "${scheduler.refund-query:0 */5 * * * *}")
    public void processRefundOrders() {
        log.info("Starting refund order processing...");

        processOrdersByStatus(RefundStatus.APPROVED, true);
        processOrdersByStatus(RefundStatus.REFUNDING, false);

        log.info("Refund order processing completed.");
    }

    private void processOrdersByStatus(RefundStatus status, boolean executeRefund) {
        int page = 0;
        Page<RefundOrder> orders;
        do {
            orders = refundOrderRepository.findByStatusIn(
                List.of(status), PageRequest.of(page, BATCH_SIZE)
            );

            for (RefundOrder refundOrder : orders.getContent()) {
                try {
                    PaymentOrder paymentOrder = paymentOrderRepository
                        .findByPaymentOrderNo(refundOrder.getPaymentOrderNo())
                        .orElseThrow(() -> new ApplicationException(PaymentMessage.PAYMENT_ORDER_NOT_FOUND));

                    if (executeRefund) {
                        refundAppService.processApprovedRefund(refundOrder, paymentOrder);
                    } else {
                        refundAppService.processRefundingOrder(refundOrder, paymentOrder);
                    }
                } catch (Exception e) {
                    log.error("Failed to process refund order {}: {}",
                        refundOrder.getRefundOrderNo(), e.getMessage());
                }
            }
            page++;
        } while (orders.hasNext());
    }
}