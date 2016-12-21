DROP TABLE IF EXISTS owned_users;
DROP TABLE IF EXISTS multi_provider_authenticated;
DROP TABLE IF EXISTS authentication_provider;

CREATE TABLE signup_users(
  email VARCHAR (64),
  password_hash VARCHAR(128) NOT NULL,
  user_name VARCHAR (32) NOT NULL,
  user_id VARCHAR (64) NOT NULL UNIQUE,
  PRIMARY KEY (email),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE authentication_providers(
  id VARCHAR (32),
  PRIMARY KEY (id)
);
INSERT INTO authentication_providers(id) values ('signup');
INSERT INTO authentication_providers(id) values ('twitter');

CREATE TABLE user_authenticated_providers(
  user_id VARCHAR (64),
  authentication_provider_id VARCHAR (32),
  PRIMARY KEY (user_id, authentication_provider_id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (authentication_provider_id) REFERENCES authentication_providers(id)
);