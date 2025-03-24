-- Foreign key from sections to form_users
ALTER TABLE sections
ADD CONSTRAINT fk_sections_form_user
FOREIGN KEY (id_form_section) REFERENCES form_users(id_form_user);

-- Foreign key from form_users to users
ALTER TABLE form_users
ADD CONSTRAINT fk_form_users_user
FOREIGN KEY (id_user) REFERENCES users(id_user);

-- Fix the questions table foreign key constraint
ALTER TABLE questions
DROP CONSTRAINT IF EXISTS fk_section_question;

ALTER TABLE questions
ADD CONSTRAINT fk_section_question
FOREIGN KEY (id_section) REFERENCES sections(id_section);