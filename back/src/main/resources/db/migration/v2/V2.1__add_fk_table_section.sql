ALTER TABLE sections
ADD COLUMN id_form_user BIGINT;

ALTER TABLE sections
ADD CONSTRAINT fk_sections_form_user
FOREIGN KEY (id_form_user) REFERENCES form_user(id);