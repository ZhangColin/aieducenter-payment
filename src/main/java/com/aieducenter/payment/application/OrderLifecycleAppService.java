package com.aieducenter.payment.application;

import com.aieducenter.payment.application.dto.response.OrderLifecycleResponse;
import com.aieducenter.payment.application.mapper.OrderLifecycleMapper;
import com.aieducenter.payment.domain.aggregate.OperationLog;
import com.aieducenter.payment.domain.aggregate.PaymentLog;
import com.aieducenter.payment.domain.repository.OperationLogRepository;
import com.aieducenter.payment.domain.repository.PaymentLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 订单生命周期读模型应用服务（读侧）。
 *
 * <p>按 {@code orderNo}（支付订单号）合并该订单的 PaymentLog（既有按单查询）与 OperationLog（T1 的按 targetNo 查询），
 * 各自映射为统一事件后按 {@code createdAt} 升序返回——「这单从创建到终态经历了什么」
 * （CONTEXT.md · 订单生命周期；ADR-0002 · 不合表、合视图）。</p>
 *
 * <p><b>只读</b>：{@code @Transactional(readOnly = true)}，不落库、不改订单状态（ADR-0001）。银行无关（CONTEXT.md 不变式 5）。</p>
 */
@Service
@RequiredArgsConstructor
public class OrderLifecycleAppService {

    private final PaymentLogRepository paymentLogRepository;
    private final OperationLogRepository operationLogRepository;
    private final OrderLifecycleMapper orderLifecycleMapper;

    /**
     * 查询某支付订单的完整生命周期事件序列。
     *
     * @param orderNo 支付订单号
     * @return 按 createdAt 升序合并的事件序列（每条标注来源 GATEWAY/OPERATION）；无记录时返回空列表
     */
    @Transactional(readOnly = true)
    public List<OrderLifecycleResponse> getLifecycle(String orderNo) {
        // 仓储方法按 createdAt DESC 返回（列表场景约定）；此处合并后显式重排为升序（时间线正序）。
        List<PaymentLog> paymentLogs = paymentLogRepository.findByPaymentOrderNoOrderByCreatedAtDesc(orderNo);
        List<OperationLog> operationLogs = operationLogRepository.findByTargetNoOrderByCreatedAtDesc(orderNo);

        // 不预分配容量：单订单事件有界且小，预分配 size()+size() 仅引来 pitest「+→-」良性假阳性（容量提示，ArrayList 自适应扩容）。
        List<OrderLifecycleResponse> events = new ArrayList<>();
        for (PaymentLog paymentLog : paymentLogs) {
            events.add(orderLifecycleMapper.fromPaymentLog(paymentLog));
        }
        for (OperationLog operationLog : operationLogs) {
            events.add(orderLifecycleMapper.fromOperationLog(operationLog));
        }

        events.sort(
            Comparator.comparing(OrderLifecycleResponse::createdAt)
                .thenComparing(OrderLifecycleResponse::id)
        );
        return events;
    }
}
