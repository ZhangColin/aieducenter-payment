package com.aieducenter.payment.domain.repository;

import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.cartisan.data.jpa.repository.BaseRepository;

import java.util.List;

/**
 * 操作日志仓储接口。
 *
 * <p>领域层定义，基础设施层由 Spring Data 自动生成实现。多条件分页查询复用
 * {@code BaseRepository} 继承的 {@code findAll(Specification, Pageable)}。</p>
 */
public interface OperationLogRepository extends BaseRepository<OperationLog, Long> {

    /**
     * 按目标单号查询操作日志，按创建时间倒序（供订单生命周期读模型合并用）。
     *
     * @param targetNo 目标单号
     * @return 操作日志列表
     */
    List<OperationLog> findByTargetNoOrderByCreatedAtDesc(String targetNo);
}
