-- ========================================================================
-- Payment Context: RefundOrder 引入 auditType（AUTO/MANUAL）
-- 把「审核类型」从隐式哨兵（auditor_name='SYSTEM'）提升为显式枚举列。
-- 免审（创建即自动通过）→ AUTO(1)；人工审核（操作者放行/拒绝）→ MANUAL(2)。
-- 历史回填：auditor_id 非空 → MANUAL，否则 → AUTO（见 CONTEXT.md auditType 词条）。
--
-- 列可空：待审核（PENDING，needAudit=true）订单尚未发生审核动作，audit_type 保持 NULL，
-- 与既有审核字段（auditor_id/auditor_name/audit_agreed/audited_at/audit_remark）一致——
-- 这些字段在审核动作发生前均为空。AUTO/MANUAL 仅在 autoAudit()/audit() 调用后赋值。
-- ========================================================================

ALTER TABLE pay_refund_orders ADD COLUMN audit_type INTEGER;

UPDATE pay_refund_orders
SET audit_type = CASE WHEN auditor_id IS NOT NULL THEN 2 ELSE 1 END;
