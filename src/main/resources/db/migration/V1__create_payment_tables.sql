-- ========================================================================
-- Payment Context: Payment Orders
-- ========================================================================

CREATE TABLE pay_payment_orders (
    id BIGINT PRIMARY KEY,
    business_order_no VARCHAR(64) NOT NULL,
    payment_order_no VARCHAR(64) NOT NULL UNIQUE,
    business_system_name VARCHAR(128) NOT NULL,
    business_name VARCHAR(128),
    status INTEGER NOT NULL,
    amount BIGINT NOT NULL,
    actual_amount BIGINT,
    subject VARCHAR(255),
    body TEXT,
    payment_channel INTEGER,
    client_ip VARCHAR(64),
    notify_url VARCHAR(512),
    qr_code_url VARCHAR(512),
    attach TEXT,
    expired_seconds BIGINT,

    -- Timestamps for status transitions
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    paid_at TIMESTAMP,
    failed_at TIMESTAMP,
    cancelled_at TIMESTAMP,
    expired_at TIMESTAMP,

    -- Bank information
    bank_order_no VARCHAR(64),
    third_party_order_no VARCHAR(128),

    -- Audit fields
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Indexes
CREATE INDEX idx_payment_orders_business_no ON pay_payment_orders(business_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_payment_orders_system ON pay_payment_orders(business_system_name) WHERE deleted = FALSE;
CREATE INDEX idx_payment_orders_status ON pay_payment_orders(status) WHERE deleted = FALSE;
CREATE INDEX idx_payment_orders_created_at ON pay_payment_orders(created_at) WHERE deleted = FALSE;

-- ========================================================================
-- Payment Context: Refund Orders
-- ========================================================================
CREATE TABLE pay_refund_orders (
    id BIGINT PRIMARY KEY,
    business_order_no VARCHAR(64) NOT NULL,
    refund_order_no VARCHAR(64) NOT NULL UNIQUE,
    payment_order_no VARCHAR(64) NOT NULL,
    business_system_name VARCHAR(128) NOT NULL,
    business_name VARCHAR(128),
    notify_url VARCHAR(512),
    status INTEGER NOT NULL,
    refund_amount BIGINT NOT NULL,
    refundable_amount BIGINT NOT NULL,
    reason VARCHAR(512),

    -- Audit information
    auditor_id BIGINT,
    auditor_name VARCHAR(64),
    audit_agreed BOOLEAN,
    audited_at TIMESTAMP,
    audit_remark TEXT,

    -- Timestamps for status transitions
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    approved_at TIMESTAMP,
    refunded_at TIMESTAMP,
    failed_at TIMESTAMP,

    -- Bank information
    bank_refund_no VARCHAR(64),

    -- Other
    attach TEXT,

    -- Audit fields
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Indexes
CREATE INDEX idx_refund_orders_payment_no ON pay_refund_orders(payment_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_refund_orders_business_no ON pay_refund_orders(business_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_refund_orders_system ON pay_refund_orders(business_system_name) WHERE deleted = FALSE;
CREATE INDEX idx_refund_orders_status ON pay_refund_orders(status) WHERE deleted = FALSE;

-- ========================================================================
-- Payment Context: Payment Logs
-- ========================================================================

CREATE TABLE pay_payment_logs (
    id BIGINT PRIMARY KEY,
    payment_order_no VARCHAR(64),
    refund_order_no VARCHAR(64),
    log_type VARCHAR(32) NOT NULL,
    bank_code VARCHAR(16) NOT NULL,
    bank_interface VARCHAR(64) NOT NULL,
    request_url VARCHAR(512),
    request_params TEXT,
    response_params TEXT,
    http_status INTEGER,
    return_code VARCHAR(16),
    return_msg TEXT,
    execution_time BIGINT,
    success BOOLEAN NOT NULL,
    error_message TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,
    deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- Indexes
CREATE INDEX idx_payment_logs_payment_no ON pay_payment_logs(payment_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_payment_logs_refund_no ON pay_payment_logs(refund_order_no) WHERE deleted = FALSE;
CREATE INDEX idx_payment_logs_created_at ON pay_payment_logs(created_at) WHERE deleted = FALSE;
