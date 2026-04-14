package com.aieducenter.payment.application.dto.callback;

import com.alibaba.fastjson2.annotation.JSONField;

/**
 * 工行回调解析后的业务数据
 *
 * <p>对应工行回调 biz_content 中的字段（snake_case JSON → camelCase Java）</p>
 */
public record IcbcCallbackParam(
    @JSONField(name = "return_code") String returnCode,
    @JSONField(name = "return_msg") String returnMsg,
    @JSONField(name = "returnCode") String returnCode2,
    @JSONField(name = "msg_id") String msgId,
    @JSONField(name = "out_trade_no") String outTradeNo,
    @JSONField(name = "order_id") String orderId,
    @JSONField(name = "total_amt") String totalAmt,
    @JSONField(name = "payment_amt") String paymentAmt,
    @JSONField(name = "pay_time") String payTime,
    @JSONField(name = "pay_type") String payType,
    @JSONField(name = "access_type") String accessType,
    @JSONField(name = "card_no") String cardNo,
    @JSONField(name = "card_flag") String cardFlag,
    @JSONField(name = "decr_flag") String decrFlag,
    @JSONField(name = "open_id") String openId,
    @JSONField(name = "third_trade_no") String thirdTradeNo,
    @JSONField(name = "total_disc_amt") String totalDiscAmt,
    @JSONField(name = "point_amt") String pointAmt,
    @JSONField(name = "ecoupon_amt") String ecouponAmt,
    @JSONField(name = "mer_disc_amt") String merDiscAmt,
    @JSONField(name = "coupon_amt") String couponAmt,
    @JSONField(name = "bank_disc_amt") String bankDiscAmt,
    @JSONField(name = "attach") String attach,
    @JSONField(name = "mer_id") String merId
) {
    /**
     * 判断支付是否成功
     */
    public boolean isPaymentSuccess() {
        return "0".equals(returnCode);
    }
}
