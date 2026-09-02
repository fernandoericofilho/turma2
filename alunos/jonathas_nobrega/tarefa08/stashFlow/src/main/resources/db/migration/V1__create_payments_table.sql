CREATE TABLE payments (
                          id UUID PRIMARY KEY,
                          customer_id VARCHAR(100) NOT NULL,
                          amount NUMERIC(19, 2) NOT NULL,
                          status VARCHAR(20) NOT NULL,
                          created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                          updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

                          CONSTRAINT ck_payments_amount_positive
                              CHECK (amount > 0),

                          CONSTRAINT ck_payments_status
                              CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED'))
);

CREATE INDEX idx_payments_customer_created_at
    ON payments (customer_id, created_at DESC);