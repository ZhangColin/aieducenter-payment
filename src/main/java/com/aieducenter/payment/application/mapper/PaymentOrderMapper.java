package com.aieducenter.payment.application.mapper;

import com.aieducenter.payment.application.dto.response.PaymentOrderResponse;
import com.aieducenter.payment.domain.aggregate.PaymentOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * 支付订单映射器
 *
 * <p>使用 MapStruct 进行领域对象与 DTO 之间的转换。static {@link #convert} 是全仓唯一转换真源
 * （被 PaymentAppService / PaymentCallbackAppService 使用）；{@link #convertList} 委托 static convert，
 * 保证列表行与详情 {@code getPayment} 输出逐字段一致。</p>
 */
@Mapper(componentModel = "spring")
public interface PaymentOrderMapper {

    /**
     * 转换为响应 DTO
     *
     * @param paymentOrder 支付订单聚合根
     * @return 响应 DTO
     */
    @Mapping(target = "status", source = "status.code")
    @Mapping(target = "statusName", source = "status.name")
    PaymentOrderResponse toResponse(PaymentOrder paymentOrder);

    /**
     * 静态便捷方法
     *
     * @param paymentOrder 支付订单聚合根
     * @return 响应 DTO
     */
    static PaymentOrderResponse convert(PaymentOrder paymentOrder) {
        return new PaymentOrderResponse(
            paymentOrder.getId(),
            paymentOrder.getBusinessOrderNo(),
            paymentOrder.getPaymentOrderNo(),
            paymentOrder.getBusinessSystemName(),
            paymentOrder.getBusinessName(),
            paymentOrder.getStatus().getCode(),
            paymentOrder.getStatus().getName(),
            paymentOrder.getAmount(),
            paymentOrder.getSubject(),
            paymentOrder.getBody(),
            paymentOrder.getPaymentChannel() != null ? paymentOrder.getPaymentChannel().getName() : null,
            paymentOrder.getQrCodeUrl(),
            paymentOrder.getClientIp(),
            paymentOrder.getCreatedAt(),
            paymentOrder.getExpiredAt(),
            paymentOrder.getPaidAt(),
            paymentOrder.getBankOrderNo(),
            paymentOrder.getThirdPartyOrderNo()
        );
    }

    /**
     * 批量转换为响应 DTO（委托 static {@link #convert}，保证与详情输出一致）。
     *
     * @param paymentOrders 支付订单聚合根列表
     * @return 响应 DTO 列表
     */
    default List<PaymentOrderResponse> convertList(List<PaymentOrder> paymentOrders) {
        return paymentOrders.stream().map(PaymentOrderMapper::convert).toList();
    }
}
