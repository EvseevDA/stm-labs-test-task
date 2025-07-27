INSERT INTO security.role (name)
VALUES
    ('ROLE_PURCHASER'),
    ('ROLE_ADMIN');

INSERT INTO bl.app_user (login, password, full_name, role_id)
VALUES (
        'admin125',
        '$2a$10$0Yn2BfyFjGqMvL5h8vGgM.ZvI3wB/3EbHjy6BPXdUv7y2fZvUElP6',
        'Админ',
        (SELECT id FROM security.role WHERE name='ROLE_ADMIN')
);