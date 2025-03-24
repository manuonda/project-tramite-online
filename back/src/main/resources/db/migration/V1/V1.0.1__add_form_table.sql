-- Table form_user
CREATE TABLE IF NOT EXISTS form_users (
    id_form_user BIGSERIAL PRIMARY KEY,            -- ID auto-incremental
    name VARCHAR(50) NOT NULL,
    description TEXT,
    created_by VARCHAR(255) NULL,
    created_date TIMESTAMP NULL,
    last_modified_by VARCHAR(255) NULL,
    last_modified_date TIMESTAMP,
    id_user BIGINT
);