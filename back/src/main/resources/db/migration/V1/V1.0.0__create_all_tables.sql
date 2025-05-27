-- ===================================
-- TRAMITE ONLINE - DATABASE SCHEMA
-- Version: 1.0.0
-- Description: Create all tables with relationships
-- ===================================

-- Table: users
CREATE TABLE users (
    id_user BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    provider_type VARCHAR(50),
    provider_id VARCHAR(255),
    password VARCHAR(255),
    created_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table: form_users
CREATE TABLE form_users (
    id_form_user BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    id_user BIGINT NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_form_users_user 
        FOREIGN KEY (id_user) REFERENCES users(id_user)
);

-- Table: sections
CREATE TABLE sections (
    id_section BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    enabled BOOLEAN DEFAULT true,
    section_type VARCHAR(50),
    id_form_user BIGINT NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_sections_form_user 
        FOREIGN KEY (id_form_user) REFERENCES form_users(id_form_user)
);

-- Table: questions
CREATE TABLE questions (
    id_question BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    question_type VARCHAR(50),
    section_id BIGINT NOT NULL,
    created_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_questions_section 
        FOREIGN KEY (section_id) REFERENCES sections(id_section) ON DELETE CASCADE
);

-- ===================================
-- SEQUENCES (si son necesarias)
-- ===================================

-- Sequence for sections (según tu entidad)
CREATE SEQUENCE IF NOT EXISTS section_id_seq 
    START WITH 1 
    INCREMENT BY 1;

-- ===================================
-- INDEXES for performance
-- ===================================

-- Index on email for faster user lookups
CREATE INDEX idx_users_email ON users(email);

-- Index on user_name for authentication
CREATE INDEX idx_users_username ON users(user_name);

-- Index on provider_id for OAuth2 lookups
CREATE INDEX idx_users_provider_id ON users(provider_id);

-- Index on form_users name for faster searches
CREATE INDEX idx_form_users_name ON form_users(name);

-- Index on sections name
CREATE INDEX idx_sections_name ON sections(name);

-- ===================================
-- COMMENTS for documentation
-- ===================================

COMMENT ON TABLE users IS 'User accounts for both traditional and OAuth2 authentication';
COMMENT ON TABLE form_users IS 'Forms created by users';
COMMENT ON TABLE sections IS 'Sections within forms';
COMMENT ON TABLE questions IS 'Questions within sections';

COMMENT ON COLUMN users.provider_type IS 'OAuth2 provider type (GOOGLE, FACEBOOK, etc.)';
COMMENT ON COLUMN users.provider_id IS 'Unique ID from OAuth2 provider';
COMMENT ON COLUMN sections.section_type IS 'Type of section (PERSONAL_INFO, DOCUMENTS, etc.)';
COMMENT ON COLUMN questions.question_type IS 'Type of question (TEXT, CHECKBOX, RADIO, etc.)';