package com.tramite.online.config.security.service.extractor;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.tramite.online.config.security.model.UserInfo;
import com.tramite.online.config.security.service.OAuth2UserInfoExtractorStrategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Component
@Slf4j
public class FacebookOAuth2UserInfoExtractor implements OAuth2UserInfoExtractorStrategy{
    
    @Override
    public UserInfo extractUserInfo(OAuth2User oAuth2User) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'extractUserInfo'");
    }

}
