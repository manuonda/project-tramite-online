ALTER TABLE form_users
ADD COLUMN user_id BIGINT,
ADD CONSTRAINT fk_user_form_user FOREIGN KEY (user_id) REFERENCES users(id);