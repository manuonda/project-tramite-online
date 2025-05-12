package com.tramite.online.config.security.oauth2.handler;

import java.io.IOException;
import java.security.DrbgParameters;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.tramite.online.config.ApplicationProperties;
import com.tramite.online.config.security.jwt.TokenProvider;
import com.tramite.online.config.security.oauth2.factory.OAuth2UserInfoExtractorFactory;
import com.tramite.online.config.security.oauth2.service.OAuth2UserInfoExtractor;
import com.tramite.online.service.UserManagementService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

 /**
     * This class handles the successful authentication process for OAuth2 users.
     * It utilizes the OAuth2UserInfoExtractorFactory to extract user information
     * from the OAuth2 authentication response and perform any necessary post-login
     * actions, such as token generation or user data processing.
     */

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final ApplicationProperties appProperties;
    private final UserManagementService userManagementService;
    private final OAuth2UserInfoExtractorFactory extractorFactory;


    

    public OAuth2AuthenticationSuccessHandler(
        TokenProvider tokenProvider,  ApplicationProperties appProperties , 
        UserManagementService userManagementService, OAuth2UserInfoExtractorFactory extractorFactory) {
         this.tokenProvider = tokenProvider;
        this.appProperties = appProperties;
        this.userManagementService = userManagementService;
        this.extractorFactory = extractorFactory;
    }




    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
       String targetUrl = determineTargetUrl(request, response);
       if(response.isCommitted()){
         logger.debug("Response has already been committed. Unable to redirect to  "+ targetUrl);
         return;
       }

       clearAuthenticationAttributes(request,response);
       getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }


    

    

 



   
}