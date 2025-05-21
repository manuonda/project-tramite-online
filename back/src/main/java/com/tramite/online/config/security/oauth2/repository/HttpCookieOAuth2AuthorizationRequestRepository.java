package com.tramite.online.config.security.oauth2.repository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import com.tramite.online.config.security.oauth2.service.util.CookieUtils;

import io.micrometer.common.util.StringUtils;



/**
 * Custom implementation of AuthorizationRequestRepository that stores
 * and retrieves OAuth2 authorization requests using HTTP cookies.
 * 
 * This class is crucial for the OAuth2 authentication flow in stateless
 * applications, as it maintains information between redirects without
 * relying on server sessions. It stores both the OAuth2 request and
 * the final redirect URI to which the user should be sent after
 * successful authentication.
 */


@Component
public class HttpCookieOAuth2AuthorizationRequestRepository  implements 
AuthorizationRequestRepository<OAuth2AuthorizationRequest>{

     /**
     * Name of the cookie that stores the serialized OAuth2 authorization request
     */
    public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
     /**
     * Name of the cookie that stores the URI to redirect to after successful authentication
     */
    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
      /**
     * Cookie lifetime in seconds (3 minutes)
     */
    private static final int COOKIE_EXPIRE_SECONDS = 180; // 3 minutos

     /**
     * Loads the OAuth2 authorization request from cookies.
     * 
     * Spring Security calls this method to retrieve the request
     * when the OAuth2 provider redirects back to the application.
     * 
     * @param request The incoming HTTP request
     * @return The deserialized OAuth2 authorization request, or null if it doesn't exist
     */
    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
        return CookieUtils.getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
                .map(cookie -> CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
                .orElse(null);
    }

    /**
     * Saves the OAuth2 authorization request in cookies.
     * 
     * Spring Security calls this method before redirecting the user
     * to the OAuth2 provider for authentication. It also stores the final
     * redirect URI if present in the request parameters.
     * 
     * @param authorizationRequest The OAuth2 authorization request to save
     * @param request The incoming HTTP request
     * @param response The HTTP response where cookies will be added
     */
    @Override
    public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request, HttpServletResponse response) {
        if (authorizationRequest == null) {
            CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
            CookieUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
            return;
        }

        CookieUtils.addCookie(response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME, CookieUtils.serialize(authorizationRequest), COOKIE_EXPIRE_SECONDS);
        String redirectUriAfterLogin = request.getParameter(REDIRECT_URI_PARAM_COOKIE_NAME);
        if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
            CookieUtils.addCookie(response, REDIRECT_URI_PARAM_COOKIE_NAME, redirectUriAfterLogin, COOKIE_EXPIRE_SECONDS);
        }
    }

      /**
     * Retrieves and removes the OAuth2 authorization request.
     * 
     * Spring Security calls this method during the authentication process
     * to obtain the original request and continue the flow.
     * 
     * @param request The incoming HTTP request
     * @param response The HTTP response
     * @return The stored OAuth2 authorization request
     */
    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request, HttpServletResponse response) {
         return this.loadAuthorizationRequest(request);
    }

      /**
     * Removes cookies related to the OAuth2 authorization request.
     * 
     * This method is called from the OAuth2 authentication success or failure handler
     * after completing the flow to clean up the temporary cookies.
     * 
     * @param request The incoming HTTP request
     * @param response The HTTP response where cookies will be deleted
     */
    public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
        CookieUtils.deleteCookie(request, response, REDIRECT_URI_PARAM_COOKIE_NAME);
    }

}
