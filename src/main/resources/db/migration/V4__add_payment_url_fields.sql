-- 重命名现有 pay_url 为 cshdk_url（实际存储的是收银台URL）
ALTER TABLE hsb_payment_orders RENAME COLUMN pay_url TO cshdk_url;

-- 新增 pay_url 列（存储建行 Pay_Url）
ALTER TABLE hsb_payment_orders ADD COLUMN pay_url VARCHAR(512);

-- 新增页面返回URL列（建行字段: Pgfc_Ret_Url_Adr）
ALTER TABLE hsb_payment_orders ADD COLUMN page_return_url VARCHAR(512);