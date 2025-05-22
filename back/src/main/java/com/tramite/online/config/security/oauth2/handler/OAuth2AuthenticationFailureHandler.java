
package com.tramite.online.config.security.oauth2.handler;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.tramite.online.config.security.oauth2.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import static com.tramite.online.config.security.oauth2.repository.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;
import com.tramite.online.config.security.oauth2.service.util.CookieUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This class is a custom implementation of the {@link SimpleUrlAuthenticationFailureHandler}
 * to handle OAuth2 authentication failures in the application.
 * 
 * It is annotated with {@link Component} to allow Spring to manage its lifecycle as a bean.
 * The class utilizes {@link HttpCookieOAuth2AuthorizationRequestRepository} to manage
 * OAuth2 authorization requests stored in cookies.
 * 
 * The {@code onAuthenticationFailure} method is overridden to provide custom behavior
 * when an authentication failure occurs during the OAuth2 login process.
 * 
 * This handler can be extended to log errors, redirect users to specific error pages,
 * or perform other custom actions upon authentication failure.
 */

@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

   

    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    public OAuth2AuthenticationFailureHandler(com.tramite.online.config.security.oauth2.repository.HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
        this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
    }
    

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
       String targetUrl = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
       .map(Cookie::getValue)
       .orElse(("/"));

       targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
       .queryParam("error", exception.getLocalizedMessage())
       .build().toUriString();

       httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequest(request, response);
       getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    
}
