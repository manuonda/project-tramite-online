package com.tramite.online.config;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.tramite.online.config.security.jwt.JwtTokenService;
import com.tramite.online.service.UserManagementService;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    private final JwtTokenService jwTokenService;
    private final UserManagementService userManagementService;


    public OAuth2AuthenticationSuccessHandler(
        JwtTokenService jwtTokenService,
        UserManagementService userManagementService
    ){
        this.jwtTokenService = jwtTokenService;
        this.userManagementService = userManagementService;
    }

}
