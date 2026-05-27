package com.aieducenter.payment.hsb.domain.repository;

import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentOrder;
import com.aieducenter.payment.hsb.domain.enums.HsbPaymentStatus;
import com.cartisan.data.jpa.repository.BaseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface HsbPaymentOrderRepository extends BaseRepository<HsbPaymentOrder, Long> {
    Optional<HsbPaymentOrder> findByPaymentOrderNo(String paymentOrderNo);
    Optional<HsbPaymentOrder> findByBusinessMainOrderNo(String businessMainOrderNo);
    boolean existsByPaymentOrderNo(String paymentOrderNo);
    List<HsbPaymentOrder> findByStatusAndExpiredAtBefore(HsbPaymentStatus status, LocalDateTime expiredAt);
}
