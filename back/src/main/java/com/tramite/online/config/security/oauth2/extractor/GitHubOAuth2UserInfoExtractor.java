package com.tramite.online.config.security.oauth2.extractor;

import java.lang.ProcessBuilder.Redirect.Type;

import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.tramite.online.config.security.model.TypeProvider;
import com.tramite.online.config.security.model.UserInfo;
import com.tramite.online.config.security.oauth2.service.CustomOAuth2UserService;
import com.tramite.online.config.security.oauth2.service.OAuth2UserInfoExtractor;
import com.tramite.online.config.security.oauth2.service.ProviderAwareOAuth2UserInfoExtractor;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GitHubOAuth2UserInfoExtractor implements ProviderAwareOAuth2UserInfoExtractor{
    

    @Override
    public UserInfo extractUserInfo(OAuth2User oAuth2User) {
        log.info("Extracting user info from GitHub OauthUser : {}", oAuth2User);
        
        Object idAttribute = oAuth2User.getAttribute("id");
        String id = (idAttribute != null) ? idAttribute.toString() : null;
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        String picture = oAuth2User.getAttribute("avatar_url");
        String login = oAuth2User.getAttribute("login");
        name = (name != null) ? name: login;

        return UserInfo.builder()
        .id(id)
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
