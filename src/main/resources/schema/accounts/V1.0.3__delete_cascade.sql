
-- DROP FOREIGN KEY
ALTER TABLE signup_users DROP FOREIGN KEY signup_users_ibfk_1;

ALTER TABLE twitter_users DROP FOREIGN KEY twitter_users_ibfk_1;

ALTER TABLE user_authenticated_providers DROP FOREIGN KEY user_authenticated_providers_ibfk_1;
ALTER TABLE user_authenticated_providers DROP FOREIGN KEY user_authenticated_providers_ibfk_2;

-- re new FOREIGN KEY
ALTER TABLE signup_users ADD CONSTRAINT signup_users_ibfk_1 FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE twitter_users ADD CONSTRAINT twitter_users_ibfk_1 FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE user_authenticated_providers ADD CONSTRAINT user_authenticated_providers_ibfk_1 FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE user_authenticated_providers ADD CONSTRAINT user_authenticated_providers_ibfk_2 FOREIGN KEY (authentication_provider_id) REFERENCES authentication_providers(id) ON UPDATE CASCADE ON DELETE CASCADE;