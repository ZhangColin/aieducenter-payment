/**
 * Payment Context.
 *
 * <h3>职责</h3>
 * <ul>
 *   <li>支付订单管理</li>
 *   <li>退款订单管理（含审核流程）</li>
 *   <li>支付流水记录</li>
 *   <li>银行网关适配</li>
 * </ul>
 *
 * <h3>限界上下文</h3>
 * <p>支付核心上下文，为业务系统提供统一的支付、退款、查询功能</p>
 *
 * <h3>包结构</h3>
 * <ul>
 *   <li>domain - 领域层：支付订单、退款订单、支付日志聚合根，支付网关端口接口</li>
 *   <li>application - 应用层：支付应用服务、DTO</li>
 *   <li>infrastructure - 基础设施层：持久化、ICBC 适配器</li>
 *   <li>endpoints - 北向接口：Payment API</li>
 * </ul>
 *
 * @since 1.0.0
 */
@BoundedContext(name = "Payment", subDomain = SubDomain.CORE)
package com.aieducenter.payment;

import com.cartisan.core.stereotype.BoundedContext;
import com.cartisan.core.stereotype.SubDomain;
