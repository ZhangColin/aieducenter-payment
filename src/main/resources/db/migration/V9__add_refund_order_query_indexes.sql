-- ========================================================================
-- Payment Context: 退款订单列表查询筛选用字段索引
-- 为后台多条件分页查询 GET /api/v1/refunds 的高频筛选维度补索引（acceptance criteria）。
-- 已有索引（V1）：payment_order_no / business_order_no / business_system_name / status（+ refund_order_no UNIQUE）。
-- 本迁移补齐：audit_type（T3 新增审核类型筛选）/ auditor_id（审核者筛选）/ refund_amount（金额区间）/ created_at（创建时间区间，V1 漏建）。
-- 银行无关：退款无通道筛选，无 ICBC 硬编码。
-- ========================================================================

CREATE INDEX idx_refund_orders_audit_type ON pay_refund_orders(audit_type) WHERE deleted = FALSE;
CREATE INDEX idx_refund_orders_auditor_id ON pay_refund_orders(auditor_id) WHERE deleted = FALSE;
CREATE INDEX idx_refund_orders_refund_amount ON pay_refund_orders(refund_amount) WHERE deleted = FALSE;
CREATE INDEX idx_refund_orders_created_at ON pay_refund_orders(created_at) WHERE deleted = FALSE;
