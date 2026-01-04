CREATE TABLE payments
(
    id                               UUID                        NOT NULL,
    created_at                       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at                       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    account_id                       VARCHAR(255),
    reference_number                 VARCHAR(255),
    transaction_description          VARCHAR(255),
    amount                           DOUBLE PRECISION,
    payment_method                   VARCHAR(255),
    status                           VARCHAR(255),
    external_transaction_description VARCHAR(255),
    CONSTRAINT pk_payments PRIMARY KEY (id)
);