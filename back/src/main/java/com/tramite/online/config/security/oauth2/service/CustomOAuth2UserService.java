package com.tramite.online.config.security.oauth2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.tramite.online.config.security.model.UserInfo;
import com.tramite.online.config.security.oauth2.factory.OAuth2UserInfoExtractorFactory;
import com.tramite.online.config.security.principal.UserPrincipal;
import com.tramite.online.domain.entity.User;
import com.tramite.online.exception.OAuth2AuthenticationProcessingException;
import com.tramite.online.service.UserManagementService;

import io.micrometer.common.util.StringUtils;



/**
 * Class that allows authentication with the provider for the system's 
 * OAuth2-based authentication.
 * 
 * This class is responsible for handling the integration with OAuth2 providers 
 * and extracting user information to facilitate the authentication process.
 * 
 * @author dgarcia
 * @version 1.0
 * @since 23/03/2025
 */
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final OAuth2UserInfoExtractorFactory extractorFactory;
    private final UserManagementService userManagementService;
    private final Logger log = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    public CustomOAuth2UserService(
        OAuth2UserInfoExtractorFactory extractorFactory ,
        UserManagementService userManagementService) {
        this.extractorFactory = extractorFactory;
        this.userManagementService = userManagementService;
        log.info("Injected userInfoExtractors: {}", extractorFactory.getAllExtractors().keySet()); // Agregar este log
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("OAuth2User loadUser : {}", userRequest);

        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Obtener el ID de registro para determinar el proveedor
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        log.info("OAuth2 login attempt with provider: {}", registrationId);

        OAuth2UserInfoExtractor extractor = this.extractorFactory.getExtractor(registrationId);
        if (extractor == null) {
            log.error("Not OAuth2UserInfoExtractor Found Provider {} ", registrationId);
            throw new OAuth2AuthenticationException("Authentication method not supported" + registrationId);
        }

        UserInfo userInfo = extractor.extractUserInfo(oAuth2User);

        if (StringUtils.isEmpty(oAuth2User.getName())) {
           log.error("Email not found from OAuth2 provider");
           throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        User user =  this.userManagementService.processOAuthUser(userInfo);
    
        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }
  

}
