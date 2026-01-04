CREATE TABLE customers
(
    id                  UUID                        NOT NULL,
    created_at          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    first_name          VARCHAR(255),
    last_name           VARCHAR(255),
    email               VARCHAR(255),
    phone_number        VARCHAR(255),
    address             VARCHAR(255),
    account_id          UUID,
    fineract_account_id BIGINT,
    fineract_client_id  BIGINT,
    CONSTRAINT pk_customers PRIMARY KEY (id)
);