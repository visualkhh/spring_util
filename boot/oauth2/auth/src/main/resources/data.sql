INSERT INTO USER
(USERNAME, 		PASSWORD, 	ACCOUNTNONEXPIRED, 	ACCOUNTNONLOCKED,		CREDENTIALSNONEXPIRED, 		ENABLED) VALUES
('khh',       	'1234',		TRUE,              TRUE,                   TRUE,                       TRUE),
('visualkhh', 	'1234',		TRUE,              TRUE,                   TRUE,                       TRUE),
('hhk',       	'1234',		TRUE,              TRUE,                   TRUE,                       TRUE);

INSERT INTO AUTHORITY
(USERNAME,		AUTHORITY) VALUES
('khh',       	'ROLE_ADMIN'),
('khh',       	'ROLE_USER'),
('khh',       	'AUTH_USER_C'),
('khh',       	'AUTH_USER_R'),
('khh',       	'AUTH_USER_U'),
('khh',       	'AUTH_USER_D'),
('khh',       	'AUTH_ADMIN_C'),
('khh',       	'AUTH_ADMIN_D'),
('visualkhh', 	'ROLE_USER'),
('hhk',       	'ROLE_USER');