-- ========================================================================
-- HSB (惠市宝) Context: Payment Orders
-- ========================================================================

CREATE TABLE hsb_payment_orders (
    id BIGINT PRIMARY KEY,
    payment_order_no VARCHAR(64) NOT NULL UNIQUE,
    business_main_order_no VARCHAR(64) NOT NULL,
    business_system_name VARCHAR(128) NOT NULL,
    business_name VARCHAR(128),
    status INTEGER NOT NULL,
    mkt_id VARCHAR(32) NOT NULL,
    payment_method VARCHAR(8) NOT NULL,
    order_type VARCHAR(8) NOT NULL,
    currency VARCHAR(8) DEFAULT '156',
    total_amount BIGINT NOT NULL,
    txn_total_amount BIGINT NOT NULL,
    fee_bearer_id VARCHAR(32),
    expired_seconds BIGINT DEFAULT 3600,
    notify_url VARCHAR(512),
    attach TEXT,
    pay_url VARCHAR(512),
    pay_qr_code VARCHAR(512),
    prim_order_no VARCHAR(64),
    py_trn_no VARCHAR(64),
    actual_amount BIGINT,
    paid_at TIMESTAMP,
    failed_at TIMESTAMP,
    expired_at TIMESTAMP,

    -- Audit fields
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_hsb_payment_orders_business_no ON hsb_payment_orders(business_main_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_payment_orders_system ON hsb_payment_orders(business_system_name) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_payment_orders_status ON hsb_payment_orders(status) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_payment_orders_created_at ON hsb_payment_orders(created_at) WHERE deleted = FALSE;

-- ========================================================================
-- HSB Context: Payment Sub Orders
-- ========================================================================

CREATE TABLE hsb_payment_sub_orders (
    id BIGINT PRIMARY KEY,
    payment_order_id BIGINT NOT NULL REFERENCES hsb_payment_orders(id),
    payment_order_no VARCHAR(64),
    business_main_order_no VARCHAR(64) NOT NULL,
    business_sub_order_no VARCHAR(64) NOT NULL,
    mkt_mrch_id VARCHAR(32) NOT NULL,
    order_amount BIGINT NOT NULL,
    txn_amount BIGINT NOT NULL,
    sub_order_id VARCHAR(64),
    confirmed BOOLEAN DEFAULT FALSE,
    confirmed_at TIMESTAMP,

    -- Audit fields
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_hsb_sub_orders_payment_id ON hsb_payment_sub_orders(payment_order_id) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_sub_orders_biz_sub_no ON hsb_payment_sub_orders(business_sub_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_sub_orders_mkt_mrch_id ON hsb_payment_sub_orders(mkt_mrch_id) WHERE deleted = FALSE;

-- ========================================================================
-- HSB Context: Refund Orders
-- ========================================================================

CREATE TABLE hsb_refund_orders (
    id BIGINT PRIMARY KEY,
    refund_order_no VARCHAR(64) NOT NULL UNIQUE,
    payment_order_id BIGINT NOT NULL REFERENCES hsb_payment_orders(id),
    payment_order_no VARCHAR(64) NOT NULL,
    business_main_order_no VARCHAR(64) NOT NULL,
    business_system_name VARCHAR(128) NOT NULL,
    business_name VARCHAR(128),
    refund_type VARCHAR(16) DEFAULT 'ASYNC',
    status INTEGER NOT NULL,
    refund_amount BIGINT NOT NULL,
    reason VARCHAR(512),
    notify_url VARCHAR(512),
    attach TEXT,
    super_refund_no VARCHAR(64),
    refunded_at TIMESTAMP,
    failed_at TIMESTAMP,

    -- Audit fields
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_hsb_refund_orders_payment_id ON hsb_refund_orders(payment_order_id) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_refund_orders_business_no ON hsb_refund_orders(business_main_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_refund_orders_status ON hsb_refund_orders(status) WHERE deleted = FALSE;

-- ========================================================================
-- HSB Context: Refund Sub Orders
-- ========================================================================

CREATE TABLE hsb_refund_sub_orders (
    id BIGINT PRIMARY KEY,
    refund_order_id BIGINT NOT NULL REFERENCES hsb_refund_orders(id),
    refund_order_no VARCHAR(64),
    business_main_order_no VARCHAR(64) NOT NULL,
    business_sub_order_no VARCHAR(64) NOT NULL,
    sub_order_id VARCHAR(64),
    refund_amount BIGINT NOT NULL,

    -- Audit fields
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_hsb_refund_sub_orders_refund_id ON hsb_refund_sub_orders(refund_order_id) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_refund_sub_orders_biz_sub_no ON hsb_refund_sub_orders(business_sub_order_no) WHERE deleted = FALSE;

-- ========================================================================
-- HSB Context: Settlement Confirms
-- ========================================================================

CREATE TABLE hsb_settlement_confirms (
    id BIGINT PRIMARY KEY,
    payment_order_id BIGINT NOT NULL REFERENCES hsb_payment_orders(id),
    payment_order_no VARCHAR(64) NOT NULL,
    business_main_order_no VARCHAR(64) NOT NULL,
    status INTEGER NOT NULL,
    business_sub_order_nos JSON,
    sub_order_ids JSON,
    confirmed_at TIMESTAMP,

    -- Audit fields
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_hsb_settlement_confirms_payment_id ON hsb_settlement_confirms(payment_order_id) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_settlement_confirms_status ON hsb_settlement_confirms(status) WHERE deleted = FALSE;

-- ========================================================================
-- HSB Context: Payment Logs
-- ========================================================================

CREATE TABLE hsb_payment_logs (
    id BIGINT PRIMARY KEY,
    payment_order_no VARCHAR(64),
    refund_order_no VARCHAR(64),
    log_type VARCHAR(32),
    bank_interface VARCHAR(64),
    request_url VARCHAR(512),
    request_params TEXT,
    response_params TEXT,
    http_status INTEGER,
    return_code VARCHAR(16),
    return_msg TEXT,
    execution_time BIGINT,
    success BOOLEAN,
    error_message TEXT,

    -- Audit fields
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE INDEX idx_hsb_payment_logs_payment_no ON hsb_payment_logs(payment_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_hsb_payment_logs_refund_no ON hsb_payment_logs(refund_order_no) WHERE deleted = FALSE;
