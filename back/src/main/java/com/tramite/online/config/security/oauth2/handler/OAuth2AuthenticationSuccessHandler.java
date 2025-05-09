package com.tramite.online.config.security.oauth2.handler;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.tramite.online.config.ApplicationProperties;
import com.tramite.online.config.security.jwt.TokenProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import com.tramite.online.config.security.oauth2.factory.OAuth2UserInfoExtractorFactory;
import com.tramite.online.service.UserManagementService;


@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final ApplicationProperties appProperties;
    private final UserManagementService userManagementService;
    private final OAuth2UserInfoExtractorFactory extractorFactory;


    

    public OAuth2AuthenticationSuccessHandler(
        TokenProvider tokenProvider, 
        ApplicationProperties appProperties) {
        this.tokenProvider = tokenProvider;
        this.appProperties = appProperties;
        this.extractorFactory = null;
    }

 



   
}