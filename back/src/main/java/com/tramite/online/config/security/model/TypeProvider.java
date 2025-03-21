package com.tramite.online.config.security.model;


// Definición de un enum llamado TypeProvider, que representa diferentes tipos de proveedores de autenticación.
public enum TypeProvider {
    // Cada constante del enum representa un proveedor de autenticación con un valor asociado.
    FACEBOOK("facebook"),  // Proveedor de autenticación de Facebook
    GOOGLE("google"),      // Proveedor de autenticación de Google
    GITHUB("github"),      // Proveedor de autenticación de GitHub
    MICROSOFT("microsoft"),// Proveedor de autenticación de Microsoft
    LOCAL("local");        // Proveedor de autenticación local (usuario/contraseña)
    
    // Campo privado que almacena el valor asociado a cada constante del enum.
    private final String value;
    
    // Constructor del enum, que asigna el valor asociado a cada constante.
    // Este constructor es privado por defecto en los enums.
    TypeProvider(String value) {
        this.value = value;
    }
    
    // Método público para obtener el valor asociado a una constante del enum.
    public String getValue() {
        return value;
    }

    
    /**
     * Finds the TypeProvider enum constant that corresponds to the given string value.
     * Case-insensitive.
     * 
     * @param value The provider string value
     * @return The matching TypeProvider or LOCAL as default
     */
    public static TypeProvider fromString(String value) {
        if (value == null) {
            return LOCAL; // Default provider
        }
        
        for (TypeProvider provider : TypeProvider.values()) {
            if (provider.getValue().equalsIgnoreCase(value)) {
                return provider;
            }
        }
        
        return LOCAL; // Default if no match
    }
}
