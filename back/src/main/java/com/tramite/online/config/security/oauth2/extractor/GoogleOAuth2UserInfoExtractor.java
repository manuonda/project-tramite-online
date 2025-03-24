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
package com.tramite.online.config.security.oauth2.extractor;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.tramite.online.config.security.model.TypeProvider;
import com.tramite.online.config.security.model.UserInfo;
import com.tramite.online.config.security.oauth2.service.ProviderAwareOAuth2UserInfoExtractor;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class GoogleOAuth2UserInfoExtractor implements ProviderAwareOAuth2UserInfoExtractor{
    
    @Override
    public UserInfo extractUserInfo(OAuth2User oAuth2User) {
       log.info("Extract User info from Google OAuthUser : ", oAuth2User);
       return UserInfo.builder()
       .providerId(oAuth2User.getAttribute("sub"))
       .name(oAuth2User.getAttribute("name"))
       .email(oAuth2User.getAttribute("email"))
       .picture(oAuth2User.getAttribute("picture"))
       .provider(TypeProvider.GOOGLE)
       .build();
    }

    @Override
    public TypeProvider getProviderType() {
       return TypeProvider.GOOGLE;
    }


}
