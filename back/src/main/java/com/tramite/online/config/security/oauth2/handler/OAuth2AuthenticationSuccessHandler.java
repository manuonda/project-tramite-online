package com.tramite.online.config.security.oauth2.handler;

import java.io.IOException;
import java.net.URI;
import java.security.DrbgParameters;

import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.tramite.online.config.ApplicationProperties;
import com.tramite.online.config.security.jwt.TokenProvider;
import com.tramite.online.config.security.oauth2.factory.OAuth2UserInfoExtractorFactory;
import com.tramite.online.config.security.oauth2.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import com.tramite.online.config.security.oauth2.service.OAuth2UserInfoExtractor;
import com.tramite.online.config.security.oauth2.service.util.CookieUtils;
import com.tramite.online.service.UserManagementService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This class handles the successful authentication process for OAuth2 users. It
 * utilizes the OAuth2UserInfoExtractorFactory to extract user information from
 * the OAuth2 authentication response and perform any necessary post-login
 * actions, such as token generation or user data processing.
 */

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final TokenProvider tokenProvider;
  private final ApplicationProperties appProperties;
  private final UserManagementService userManagementService;
  private final OAuth2UserInfoExtractorFactory extractorFactory;
  private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

  public OAuth2AuthenticationSuccessHandler(TokenProvider tokenProvider, ApplicationProperties appProperties,
      UserManagementService userManagementService, OAuth2UserInfoExtractorFactory extractorFactory,
      HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository) {
    this.tokenProvider = tokenProvider;
    this.appProperties = appProperties;
    this.userManagementService = userManagementService;
    this.extractorFactory = extractorFactory;
    this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    String targetUrl = determineTargetUrl(request, response);
    if (response.isCommitted()) {
      logger.debug("Response has already been committed. Unable to redirect to  " + targetUrl);
      return;
    }

    clearAuthenticationAttributes(request, response);
    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }

  /**
   * Determines the URL to redirect the user to after successful authentication.
   * This method extracts the redirect URI from the cookies, validates it,
   * generates a JWT token, and appends it to the redirect URI.
   *
   * @param request        The HTTP request
   * @param response       The HTTP response
   * @param authentication The authentication object containing user details
   * @return The URL to redirect to
   */
  protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {
    Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);

    if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
      throw new BadRequestException(
          "Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
    }

    String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

    String token = tokenProvider.createToken(authentication);

    return UriComponentsBuilder.fromUriString(targetUrl).queryParam("token", token).build().toUriString();
  }

  /**
   * Clears the authentication attributes stored in the session or cookies after
   * successful authentication. This ensures that sensitive data related to the
   * authentication process is removed to prevent misuse.
   *
   * @param request  the HTTP request
   * @param response the HTTP response
   */
  protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
    super.clearAuthenticationAttributes(request);
    httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
  }

  private boolean isAuthorizedRedirectUri(String uri) {
    URI clientRedirectUri = URI.create(uri);

    return appProperties.getOAuth2().getAuthorizedRedirectUris().stream().anyMatch(authorizedRedirectUri -> {
      // Only validate host and port. Let the clients use different paths if they want
      // to
      URI authorizedURI = URI.create(authorizedRedirectUri);
      return authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
          && authorizedURI.getPort() == clientRedirectUri.getPort();
    });
  }

}