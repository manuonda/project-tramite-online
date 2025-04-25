package com.tramite.online.config.security.oauth2.extractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.tramite.online.config.security.model.TypeProvider;
import com.tramite.online.config.security.model.UserInfo;
import com.tramite.online.config.security.oauth2.service.ProviderAwareOAuth2UserInfoExtractor;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GitHubOAuth2UserInfoExtractor implements ProviderAwareOAuth2UserInfoExtractor{
    
    private final Logger logger = LoggerFactory.getLogger(FacebookOAuth2UserInfoExtractor.class);

    @Override
    public UserInfo extractUserInfo(OAuth2User oAuth2User) {
        logger.info("Extracting user info from GitHub OauthUser : {}", oAuth2User);
        
        Object idAttribute = oAuth2User.getAttribute("id");
        String id = (idAttribute != null) ? idAttribute.toString() : null;
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        String picture = oAuth2User.getAttribute("avatar_url");
        String login = oAuth2User.getAttribute("login");
        name = (name != null) ? name: login;

        return new UserInfo.Builder()
        .providerId(id)
        .name(name)
        .email(email)
        .picture(picture)
        .provider(TypeProvider.GITHUB)
        .build();     
    }

    @Override
    public TypeProvider getProviderType() {
      return TypeProvider.GITHUB;  
    }

  

}
