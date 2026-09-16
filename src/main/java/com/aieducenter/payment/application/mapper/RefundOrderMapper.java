package com.aieducenter.payment.application.mapper;

import com.aieducenter.payment.application.dto.response.RefundOrderResponse;
import com.aieducenter.payment.domain.aggregate.RefundOrder;
import org.mapstruct.Mapper;

/**
 * 退款订单映射器
 *
 * <p>使用 MapStruct 进行领域对象与 DTO 之间的转换。static {@link #convert} 是全仓退款转换唯一真源
 * （被 {@code RefundAppService} 创建/审核/查询详情、{@code RefundOrderQueryAppService} 列表共用），
 * 保证列表行与详情 {@code queryRefund} 输出逐字段一致。全仓均为 static 调用，不注册 spring bean。</p>
 *
 * <p>注：{@code auditType} 列可空（PENDING 态尚未发生审核动作），{@link #convert} 对
 * {@code getAuditType() == null} 空安全处理（code/name 落 null），与详情输出一致。</p>
 */
@Mapper
public interface RefundOrderMapper {

    /**
     * 静态便捷方法（全仓退款转换唯一真源）。
     *
     * @param refundOrder 退款订单聚合根
     * @return 响应 DTO
     */
    static RefundOrderResponse convert(RefundOrder refundOrder) {
        return new RefundOrderResponse(
            refundOrder.getId(),
            refundOrder.getBusinessOrderNo(),
            refundOrder.getRefundOrderNo(),
            refundOrder.getPaymentOrderNo(),
            refundOrder.getBusinessSystemName(),
            refundOrder.getBusinessName(),
            refundOrder.getStatus().getCode(),
            refundOrder.getStatus().getName(),
            refundOrder.getRefundAmount(),
            refundOrder.getRefundableAmount(),
            refundOrder.getReason(),
            refundOrder.getAuditorName(),
            refundOrder.getAuditAgreed(),
            refundOrder.getAuditRemark(),
            refundOrder.getAuditType() != null ? refundOrder.getAuditType().getCode() : null,
            refundOrder.getAuditType() != null ? refundOrder.getAuditType().getName() : null,
            refundOrder.getCreatedAt(),
            refundOrder.getApprovedAt(),
            refundOrder.getRefundedAt(),
            refundOrder.getFailedAt(),
            refundOrder.getBankRefundNo(),
            refundOrder.getNotifyUrl()
        );
    }
}
