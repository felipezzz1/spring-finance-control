CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(32) NOT NULL
);

ALTER TABLE transactions
ADD COLUMN user_id BIGINT;

ALTER TABLE transactions
ADD CONSTRAINT fk_transaction_user
FOREIGN KEY (user_id)
REFERENCES users(id);

