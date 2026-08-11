-- ========================================================================
-- Payment Context: Operation Logs
-- 行为者对订单发起的写操作留痕（审核通过/拒绝、通知重发等）。
-- 与 pay_payment_logs（网关交互日志）职责分离（见 ADR-0002）。追加只写。
-- ========================================================================

CREATE TABLE pay_operation_logs (
    id BIGINT PRIMARY KEY,
    target_type INTEGER NOT NULL,
    target_no VARCHAR(64) NOT NULL,
    operation INTEGER NOT NULL,
    operator_id BIGINT,
    operator_name VARCHAR(64),
    operator_system VARCHAR(128),
    result VARCHAR(32) NOT NULL,
    remark TEXT,

    -- Audit fields（AuditableSoftDeletable）
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- 索引覆盖 target_no / created_at / operator_id（acceptance criteria）
CREATE INDEX idx_operation_logs_target_no ON pay_operation_logs(target_no) WHERE deleted = FALSE;
CREATE INDEX idx_operation_logs_created_at ON pay_operation_logs(created_at) WHERE deleted = FALSE;
CREATE INDEX idx_operation_logs_operator_id ON pay_operation_logs(operator_id) WHERE deleted = FALSE;
