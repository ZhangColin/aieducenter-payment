package com.aieducenter.payment.hsb.domain.repository;

import com.aieducenter.payment.hsb.domain.aggregate.HsbSettlementConfirm;
import com.cartisan.data.jpa.repository.BaseRepository;

import java.util.List;

public interface HsbSettlementConfirmRepository extends BaseRepository<HsbSettlementConfirm, Long> {
    List<HsbSettlementConfirm> findByPaymentOrderNo(String paymentOrderNo);
}
