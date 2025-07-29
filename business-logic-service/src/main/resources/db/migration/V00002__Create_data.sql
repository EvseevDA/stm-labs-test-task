INSERT INTO security.role (name)
VALUES
    ('ROLE_PURCHASER'),
    ('ROLE_ADMIN');

INSERT INTO bl.app_user (login, password, full_name, role_id)
VALUES (
        'admin125',
        '$2a$10$egSIZIYZ9fAgGb0soj6OnuPSxnA7eBBDWhKg2ECii6t6wTVYEBij2',
        'Админ',
        (SELECT id FROM security.role WHERE name='ROLE_ADMIN')
);