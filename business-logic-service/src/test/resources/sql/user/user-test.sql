INSERT INTO security.role (name) VALUES ('TEST_ROLE');

INSERT INTO bl.app_user (id, login, password, full_name, role_id)
VALUES (27, 'testuser', 'testpass', 'Anatoliy', (SELECT id FROM security.role WHERE name='TEST_ROLE'));