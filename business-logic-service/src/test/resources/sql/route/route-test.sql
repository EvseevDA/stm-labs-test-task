INSERT INTO bl.carrier (id, company_name, phone)
VALUES (27, 'test company', 'test phone');

INSERT INTO bl.carrier (id, company_name, phone)
VALUES (28, 'test company', 'test phone');

INSERT INTO bl.route (id, start_point, destination_point, carrier_id, duration_in_minutes)
VALUES (27, 'test start point', 'test dest point', 27, 560);