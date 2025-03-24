-- Tabla de secciones (similar a la de preguntas)
create sequence section_id_seq start with 1 increment by 1;
CREATE TABLE IF NOT EXISTS sections (
    id_section  BIGSERIAL PRIMARY key, -- ID auto-incremental
    name VARCHAR(50) NOT NULL, -- Nombre de la sección
    description VARCHAR(100), -- Descripción de la sección (opcional)
    enabled BOOLEAN NOT NULL DEFAULT TRUE, -- Campo booleano que indica si está habilitado
    section_type VARCHAR(255), -- Tipo de la sección (enumeración)
    created_by VARCHAR(255) NOT NULL, -- Usuario que creó la sección
    created_date TIMESTAMP NOT NULL, -- Fecha de creación
    last_modified_by VARCHAR(255), -- Usuario que modificó la sección por última vez
    last_modified_date TIMESTAMP, -- Fecha de última modificación
    id_form_section BIGINT                   -- Relación con la tabla sections

);


-- Tabla de preguntas
CREATE TABLE IF NOT EXISTS questions (
    id_question BIGSERIAL PRIMARY KEY,            -- ID auto-incremental
    name VARCHAR(50) NOT NULL,           -- Texto de la pregunta
    question_type VARCHAR(255),          -- Tipo de pregunta (enumeración)
    id_section BIGINT,                   -- Relación con la tabla sections
    created_by VARCHAR(255) NOT NULL,    -- Usuario que creó la pregunta
    created_date TIMESTAMP NOT NULL,     -- Fecha de creación
    last_modified_by VARCHAR(255),       -- Usuario que modificó la pregunta por última vez
    last_modified_date TIMESTAMP,        -- Fecha de última modificación
    CONSTRAINT fk_section_question FOREIGN KEY (section_id) REFERENCES sections(id) -- Relación ManyToOne
   
);

