CREATE SCHEMA IF NOT EXISTS bl;
CREATE SCHEMA IF NOT EXISTS security;

CREATE TABLE security.role(
    id SERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE bl.app_user(
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(64) UNIQUE NOT NULL,
    password VARCHAR(256) NOT NULL,
    full_name VARCHAR(512) NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES security.role(id)
);

CREATE TABLE bl.carrier(
    id BIGSERIAL PRIMARY KEY,
    company_name VARCHAR(128) NOT NULL,
    phone VARCHAR(32) NOT NULL
);

CREATE TABLE bl.route(
    id BIGSERIAL PRIMARY KEY,
    start_point VARCHAR(512) NOT NULL,
    destination_point VARCHAR(512) NOT NULL,
    carrier_id BIGINT NOT NULL,
    FOREIGN KEY (carrier_id) REFERENCES bl.carrier(id),
    duration_in_minutes BIGINT NOT NULL
);

CREATE TABLE bl.ticket(
    id BIGSERIAL PRIMARY KEY,
    route_id BIGINT NOT NULL,
    FOREIGN KEY (route_id) REFERENCES bl.route(id),
    date_time_utc TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    seat_number INT NOT NULL,
    cost DECIMAL NOT NULL,
    passenger_id BIGINT,
    FOREIGN KEY (passenger_id) REFERENCES bl.app_user(id)
);