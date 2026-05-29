-- HSB Payment Orders: 新增确认收货日期字段（建行字段: Clrg_Dt）
ALTER TABLE hsb_payment_orders ADD COLUMN confirm_receipt_date DATE;
