CREATE SCHEMA IF NOT EXISTS main;

CREATE TABLE main.purchased_ticket(
    ticket_id BIGINT PRIMARY KEY,
    passenger_id BIGINT NOT NULL,
    cost DECIMAL NOT NULL,
    purchase_timestamp_utc TIMESTAMP WITHOUT TIME ZONE NOT NULL
);