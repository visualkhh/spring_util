INSERT INTO USER
(ID,      PWD,   NAME,  ADDR,     AUTH_ACCOUNT_NONEXPIRED, AUTH_ACCOUNT_NONLOCKED, AUTH_CREDENTIALS_NONEXPIRED, AUTH_ENABLED) VALUES
('khh',       '1234', 'kim1',  'seoul',  TRUE,                   TRUE,                   TRUE,                       TRUE),
('visualkhh', '1234', 'kim2',  'seoul',  TRUE,                   TRUE,                   TRUE,                       TRUE),
('hhk',       '1234', 'kim3',  'seoul',  TRUE,                   TRUE,                   TRUE,                       TRUE);

INSERT INTO AUTH
(ID,              AUTH,   MODIFY_DATE) VALUES
('khh',           'ROLE_ADMIN'  now()),
('khh',           'ADMIN_C'     now()),
('khh',           'ADMIN_R'     now()),
('khh',           'ADMIN_U'     now()),
('khh',           'ADMIN_D'     now()),
('khh',           'ROLE_USER'   now()),
('khh',           'USER_C'      now()),
('khh',           'USER_R'      now()),
('khh',           'USER_U'      now()),
('khh',           'USER_D'      now()),
('visualkhh',     'ROLE_USER'   now()),
('visualkhh',     'USER_C'      now()),
('visualkhh',     'USER_R'      now()),
('visualkhh',     'USER_U'      now()),
('hhk',           'ROLE_GUEST'  now());
('hhk',           'GUEST_R'     now());



INSERT INTO AUTH_URL
(AUTH,              URL_PATTERN) VALUES
('ADMIN',         '/admin**'),
('USER',          '/board**'),
('USER',          '/info**'),
('GUEST',         '/info**'),
