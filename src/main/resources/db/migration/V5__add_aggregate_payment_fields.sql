-- 新增聚合支付字段
ALTER TABLE pay_payment_orders ADD COLUMN pay_mode INTEGER;
ALTER TABLE pay_payment_orders ADD COLUMN access_type INTEGER;
ALTER TABLE pay_payment_orders ADD COLUMN shop_appid VARCHAR(32);
ALTER TABLE pay_payment_orders ADD COLUMN open_id VARCHAR(128);
ALTER TABLE pay_payment_orders ADD COLUMN prepay_data_package TEXT;
ALTER TABLE pay_payment_orders ADD COLUMN trade_type VARCHAR(16);

-- 索引
CREATE INDEX idx_payment_orders_pay_mode ON pay_payment_orders(pay_mode) WHERE deleted = FALSE;
CREATE INDEX idx_payment_orders_access_type ON pay_payment_orders(access_type) WHERE deleted = FALSE;
