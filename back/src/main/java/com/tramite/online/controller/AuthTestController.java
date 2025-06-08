package com.tramite.online.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tramite.online.service.UserManagementService;


@RestController
@RequestMapping("/api/v1/test")
public class AuthTestController {

    private final UserManagementService userManagementService;

    public AuthTestController(UserManagementService userManagementService){
        this.userManagementService = userManagementService;
    }


    @GetMapping("/me")
    public ResponseEntity<Map<String,Object>> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        Map<String, Object> response = new HashMap<>();

        if (authentication == null || !authentication.isAuthenticated()){
            response.put("authenticated", false);
            response.put("message", "Usuarion no encontrado");
            return ResponseEntity.ok(response);
        }

           response.put("authenticated", true);
        response.put("authenticationType", authentication.getClass().getSimpleName());
        response.put("authorities", authentication.getAuthorities());
        
        // Si es OAuth2User (durante el flujo OAuth2)
        if (authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            response.put("oauth2Attributes", oauth2User.getAttributes());
            response.put("principalType", "OAuth2User");
        }
                return ResponseEntity.ok(response);

    }


    /**
     * Página HTML simple para testing
     */
    @GetMapping("/dashboard")
    public String testDashboard() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>OAuth2 Test Dashboard</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 40px; }
                    .container { max-width: 800px; margin: 0 auto; }
                    .user-info { background: #f5f5f5; padding: 20px; margin: 20px 0; border-radius: 5px; }
                    .btn { padding: 10px 20px; margin: 10px; background: #007bff; color: white; text-decoration: none; border-radius: 5px; }
                    .btn:hover { background: #0056b3; }
                    pre { background: #f8f9fa; padding: 15px; border-radius: 5px; overflow-x: auto; }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>🎉 OAuth2 Login Exitoso!</h1>
                    
                    <div class="user-info">
                        <h3>Información del Usuario:</h3>
                        <div id="userInfo">Cargando...</div>
                    </div>
                    
                    <div>
                        <h3>Acciones de Testing:</h3>
                        <a href="/api/v1/test/me" class="btn">Ver Mi Info (JSON)</a>
                        <a href="/api/v1/test/oauth2-users" class="btn">Ver Todos los Usuarios</a>
                        <a href="/login" class="btn">Volver al Login</a>
                    </div>
                    
                    <div>
                        <h3>Usuarios en la Base de Datos:</h3>
                        <div id="allUsers">Cargando...</div>
                    </div>
                </div>
                
                <script>
                    // Cargar información del usuario actual
                    fetch('/api/v1/test/me')
                        .then(response => response.json())
                        .then(data => {
                            document.getElementById('userInfo').innerHTML = '<pre>' + JSON.stringify(data, null, 2) + '</pre>';
                        })
                        .catch(error => {
                            document.getElementById('userInfo').innerHTML = 'Error: ' + error.message;
                        });
                    
                    // Cargar todos los usuarios OAuth2
                    fetch('/api/v1/test/oauth2-users')
                        .then(response => response.json())
                        .then(data => {
                            document.getElementById('allUsers').innerHTML = '<pre>' + JSON.stringify(data, null, 2) + '</pre>';
                        })
                        .catch(error => {
                            document.getElementById('allUsers').innerHTML = 'Error: ' + error.message;
                        });
                </script>
            </body>
            </html>
            """;
    }
    
}
