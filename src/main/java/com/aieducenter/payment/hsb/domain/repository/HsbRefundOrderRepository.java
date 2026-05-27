package com.aieducenter.payment.hsb.domain.repository;

import com.aieducenter.payment.hsb.domain.aggregate.HsbRefundOrder;
import com.aieducenter.payment.hsb.domain.enums.HsbRefundStatus;
import com.cartisan.data.jpa.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface HsbRefundOrderRepository extends BaseRepository<HsbRefundOrder, Long> {
    Optional<HsbRefundOrder> findByRefundOrderNo(String refundOrderNo);
    List<HsbRefundOrder> findByStatus(HsbRefundStatus status);
    List<HsbRefundOrder> findByPyTrnNo(String pyTrnNo);
}
