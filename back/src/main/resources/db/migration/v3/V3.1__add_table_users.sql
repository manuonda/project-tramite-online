-- Crear la tabla users
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    provider VARCHAR(50),
    password VARCHAR(255),
    
    -- Campos de auditoría (asumiendo que Auditable<String> contiene estos campos)
    created_date TIMESTAMP,
    modified_date TIMESTAMP,
    created_by VARCHAR(255),
    modified_by VARCHAR(255)
);

-- Añadir índice para búsquedas rápidas por email
CREATE INDEX idx_users_email ON users(email);

-- Añadir índice compuesto para búsquedas por email y provider
CREATE INDEX idx_users_email_provider ON users(email, provider);

-- Comentarios para documentación
COMMENT ON TABLE users IS 'Almacena información de usuarios del sistema, tanto locales como de proveedores OAuth2';
COMMENT ON COLUMN users.id IS 'Identificador único autoincremental';
COMMENT ON COLUMN users.user_name IS 'Nombre de usuario para mostrar';
COMMENT ON COLUMN users.email IS 'Correo electrónico del usuario';
COMMENT ON COLUMN users.provider IS 'Proveedor de autenticación (google, facebook, github, microsoft, local)';
COMMENT ON COLUMN users.password IS 'Contraseña encriptada para usuarios con proveedor local';