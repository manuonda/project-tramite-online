
package com.tramite.online.config.security.oauth2.extractor;

import java.lang.ProcessBuilder.Redirect.Type;
import java.util.Map;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.tramite.online.config.security.model.TypeProvider;
import com.tramite.online.config.security.model.UserInfo;
import com.tramite.online.config.security.oauth2.service.OAuth2UserInfoExtractor;
import com.tramite.online.config.security.oauth2.service.ProviderAwareOAuth2UserInfoExtractor;

import lombok.extern.slf4j.Slf4j;

/**
 * Extracts user information from Facebook's OAuth2User object and maps 
 * it to {@link UserInfo}.
 * Implements {@link OAuth2UserInfoExtractor} for Facebook-specific logic.
 * @author  dgarcia
 * @version 1.0 
 * @since   20/3/2025
 */
@Component
@Slf4j
public class FacebookOAuth2UserInfoExtractor implements ProviderAwareOAuth2UserInfoExtractor{
    
    @Override
    public UserInfo extractUserInfo(OAuth2User oAuth2User) {
        log.info("Extracting user info from Facebook OAuth2User: {} ", oAuth2User);
        String id = oAuth2User.getAttribute("id");
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        
        //Perfil image facebook 
        String imageUrl = null; 
        Map<String, Object> pictureObj = oAuth2User.getAttribute("picture");
        if ( pictureObj != null  ) {
            Map<String, Object> data = (Map<String,Object>)pictureObj.get("data");
            if ( data != null) {
                imageUrl =(String) data.get("url");
            }

        }
        return UserInfo.builder()
        .id(id)
        .name(email)
        .name(name)
        .picture(imageUrl)
        .provider(TypeProvider.FACEBOOK)
        .build();
    }

    @Override
    public TypeProvider getProviderType() {
       return TypeProvider.FACEBOOK;
    }

}
