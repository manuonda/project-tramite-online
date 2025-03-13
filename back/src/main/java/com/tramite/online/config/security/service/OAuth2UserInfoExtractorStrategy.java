package com.tramite.online.config.security.service;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.tramite.online.config.security.model.UserInfo;

/**
 * Strategy interface for extracting user information 
 * from OAuth2 providers.
 */
public interface OAuth2UserInfoExtractorStrategy {
   UserInfo extractUserInfo(OAuth2User oAuth2User);
}
