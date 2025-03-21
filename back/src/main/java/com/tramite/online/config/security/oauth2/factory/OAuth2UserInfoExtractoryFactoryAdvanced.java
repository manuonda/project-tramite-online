
package com.tramite.online.config.security.oauth2.factory;

import java.util.List;
import java.util.Map;

import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.stereotype.Component;

import com.tramite.online.config.security.oauth2.service.OAuth2UserInfoExtractor;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OAuth2UserInfoExtractoryFactoryAdvanced {

    private final Map<String, OAuth2UserInfoExtractor> extractors;
    
    public OAuth2UserInfoExtractoryFactoryAdvanced(List<OAuth2UserInfoExtractor> extractorsList){
        return null;
    }
}
