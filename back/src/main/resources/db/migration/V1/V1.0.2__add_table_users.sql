-- Crear la tabla users
CREATE TABLE users (
    id_user BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255),
    provider_id VARCHAR(255),
    provider_type VARCHAR(100),
    
    -- Campos de auditoría (asumiendo que Auditable<String> contiene estos campos)
    created_by VARCHAR(255) NOT NULL,    -- Usuario que creó la pregunta
    created_date TIMESTAMP NOT NULL,     -- Fecha de creación
    last_modified_by VARCHAR(255),       -- Usuario que modificó la pregunta por última vez
    last_modified_date TIMESTAMP
);


