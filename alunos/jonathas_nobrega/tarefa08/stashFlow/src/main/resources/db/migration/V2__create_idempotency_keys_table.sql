CREATE TABLE idempotency_keys (
                                  id UUID PRIMARY KEY,
                                  idempotency_key VARCHAR(255) NOT NULL,
                                  request_hash VARCHAR(64) NOT NULL,
                                  payment_id UUID NOT NULL,
                                  created_at TIMESTAMP WITH TIME ZONE NOT NULL,

                                  CONSTRAINT uk_idempotency_keys_key
                                      UNIQUE (idempotency_key),

                                  CONSTRAINT fk_idempotency_keys_payment
                                      FOREIGN KEY (payment_id)
                                          REFERENCES payments(id)
);

CREATE INDEX idx_idempotency_keys_payment_id
    ON idempotency_keys (payment_id);