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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.tramite.online.config.security.model.TypeProvider;
import com.tramite.online.config.security.model.UserInfo;
import com.tramite.online.config.security.oauth2.service.ProviderAwareOAuth2UserInfoExtractor;



@Component
public class GoogleOAuth2UserInfoExtractor implements ProviderAwareOAuth2UserInfoExtractor{
        
   private final Logger logger = LoggerFactory.getLogger(GoogleOAuth2UserInfoExtractor.class);

    @Override
    public UserInfo extractUserInfo(OAuth2User oAuth2User) {
       logger.info("Extract User info from Google OAuthUser : ", oAuth2User);
       return new UserInfo.Builder()
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
