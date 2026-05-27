package com.aieducenter.payment.hsb.domain.repository;

import com.aieducenter.payment.hsb.domain.aggregate.HsbPaymentLog;
import com.cartisan.data.jpa.repository.BaseRepository;

public interface HsbPaymentLogRepository extends BaseRepository<HsbPaymentLog, Long> {
}
