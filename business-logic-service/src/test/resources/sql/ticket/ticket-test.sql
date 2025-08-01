INSERT INTO bl.app_user (id, login, password, full_name, role_id)
VALUES (27, 'test_login', 'test_pass', 'test full name', (SELECT id FROM security.role WHERE name = 'ROLE_PURCHASER'));

INSERT INTO bl.carrier (id, company_name, phone)
VALUES (27, 'test company', 'test phone');

INSERT INTO bl.route (id, start_point, destination_point, carrier_id, duration_in_minutes)
VALUES (27, 'test start point', 'test dest point', 27, 270),
       (28, 'test start point', 'test dest point', 27, 270);

INSERT INTO bl.ticket (id, route_id, date_time_utc, seat_number, cost, passenger_id)
VALUES (27, 27, '2025-07-30 20:28:39.452', 1, 290.00, NULL),
       (28, 27, '2025-07-30 20:28:39.452', 1, 290.00, 27);