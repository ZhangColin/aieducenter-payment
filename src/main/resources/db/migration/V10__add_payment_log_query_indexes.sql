-- ========================================================================
-- Payment Context: 支付网关日志列表查询筛选用字段索引
-- 为后台多条件分页查询 GET /api/v1/payment-logs 的高频筛选维度补索引（acceptance criteria）。
-- 已有索引（V1）：payment_order_no / refund_order_no / created_at。
-- 本迁移补齐：log_type（日志类型，T5 多值 IN 筛选）/ bank_interface（银行接口）/ return_code（业务返回码）。
-- success 为布尔列（基数 2），不建 B-tree 索引（与 V7/V9 不索引布尔列的既有约定一致）。
-- 银行无关：索引网关交互日志的通用列，无 ICBC 硬编码（CONTEXT.md 不变式 5）。
-- ========================================================================

CREATE INDEX idx_payment_logs_log_type ON pay_payment_logs(log_type) WHERE deleted = FALSE;
CREATE INDEX idx_payment_logs_bank_interface ON pay_payment_logs(bank_interface) WHERE deleted = FALSE;
CREATE INDEX idx_payment_logs_return_code ON pay_payment_logs(return_code) WHERE deleted = FALSE;
