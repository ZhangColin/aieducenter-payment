-- ========================================================================
-- Payment Context: 支付订单列表查询筛选用字段索引
-- 为后台多条件分页查询 GET /api/v1/payments 的高频筛选维度补索引（acceptance criteria）。
-- 已有索引（V1）：business_order_no / business_system_name / status / created_at（+ payment_order_no UNIQUE）。
-- 已有索引（V5）：pay_mode / access_type。
-- 本迁移补齐：paid_at（付款时间区间）/ payment_channel（通道筛选）/ amount（金额区间）。
-- 银行无关：仅索引抽象通道列 payment_channel，无 ICBC 硬编码。
-- ========================================================================

CREATE INDEX idx_payment_orders_paid_at ON pay_payment_orders(paid_at) WHERE deleted = FALSE;
CREATE INDEX idx_payment_orders_payment_channel ON pay_payment_orders(payment_channel) WHERE deleted = FALSE;
CREATE INDEX idx_payment_orders_amount ON pay_payment_orders(amount) WHERE deleted = FALSE;
