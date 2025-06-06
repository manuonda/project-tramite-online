
package com.tramite.online.config.security.oauth2.extractor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.tramite.online.config.security.model.TypeProvider;
import com.tramite.online.config.security.model.UserInfo;
import com.tramite.online.config.security.oauth2.service.OAuth2UserInfoExtractor;
import com.tramite.online.config.security.oauth2.service.ProviderAwareOAuth2UserInfoExtractor;



/**
 * Extracts user information from Facebook's OAuth2User object and maps it to
 * {@link UserInfo}. Implements {@link OAuth2UserInfoExtractor} for
 * Facebook-specific logic.
 * 
 * @author dgarcia
 * @version 1.0
 * @since 20/3/2025
 */
@Component
public class FacebookOAuth2UserInfoExtractor implements ProviderAwareOAuth2UserInfoExtractor {

    private final Logger logger = LoggerFactory.getLogger(FacebookOAuth2UserInfoExtractor.class);

    @Override
    public UserInfo extractUserInfo(OAuth2User oAuth2User) {
        logger.info("Extracting user info from Facebook OAuth2User: {} ", oAuth2User);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // Perfil image facebook
        String imageUrl = null;
        Map<String, Object> pictureObj = oAuth2User.getAttribute("picture");
        if (pictureObj != null) {
            Map<String, Object> data = (Map<String, Object>) pictureObj.get("data");
            if (data != null) {
                imageUrl = (String) data.get("url");
            }

        }
        return new UserInfo.Builder().providerId((String) attributes.get("id")).email((String) attributes.get("email"))
                .name((String) attributes.get("name")).picture(imageUrl).provider(TypeProvider.FACEBOOK).build();
    }

    @Override
    public TypeProvider getProviderType() {
        return TypeProvider.FACEBOOK;
    }

}
