-- used in tests that use HSQL
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table oauth_client_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONGVARBINARY,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication LONGVARBINARY
);

create table oauth_code (
  code VARCHAR(256), authentication LONGVARBINARY
);

create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);


-- customized oauth_client_details table
create table ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);


-------------
create table authority (username varchar(255) not null, authority varchar(255) not null, primary key (username, authority));
create table user (username varchar(255) not null, accountnonexpired boolean, accountnonlocked boolean, credentialsnonexpired boolean, enabled boolean, password varchar(255) not null, primary key (username));


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