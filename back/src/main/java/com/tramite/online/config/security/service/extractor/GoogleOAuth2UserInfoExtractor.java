/**
 * 
 * This class is responsible for extracting 
 * user information from Google OAuth2 responses.
 * It implements the strategy
 * defined by the OAuthUserInfoExtractorStrategy interface.
 * 
 * The extracted information can be used to authenticate 
 * and register users in the system.
 * 
 * Author: dgarcia
 * Dathe:  13/03/2025
 */
package com.tramite.online.config.security.service.extractor;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.tramite.online.config.security.model.UserInfo;
import com.tramite.online.config.security.service.OAuth2UserInfoExtractorStrategy;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class GoogleOAuth2UserInfoExtractor implements OAuth2UserInfoExtractorStrategy{
    
    @Override
    public UserInfo extractUserInfo(OAuth2User oAuth2User) {
       log.info("ExtractUserInfo from : ", oAuth2User);
       return UserInfo.builder()
       .id(oAuth2User.getAttribute("sub"))
       .name(oAuth2User.getAttribute("name"))
       .email(oAuth2User.getAttribute("email"))
       .picture(oAuth2User.getAttribute("picture"))
       .provider(oAuth2User.getAttribute("provider"))
       .build();
    }


}
