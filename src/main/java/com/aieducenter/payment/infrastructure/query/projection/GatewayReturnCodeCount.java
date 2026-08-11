package com.aieducenter.payment.infrastructure.query.projection;

/**
 * 网关 returnCode 分布读模型（issue #17，数据源 PaymentLog）。
 *
 * <p>jOOQ {@code fetchInto} 目标。按 {@code bankInterface, returnCode} 分组；
 * AppService 按 {@code bankInterface} 归组到对应接口下。</p>
 *
 * @param bankInterface 银行接口名
 * @param returnCode    业务返回码
 * @param codeCount     该返回码出现次数
 */
public record GatewayReturnCodeCount(String bankInterface, String returnCode, Long codeCount) {
}
