
package com.tramite.online.config.security.service;

import java.util.List;
import java.util.Map;

import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OAuth2UserInfoExtractoryFactoryAdvanced {

    private final Map<String, OAuth2UserInfoExtractorStrategy> extractors;
    
    public OAuth2UserInfoExtractoryFactoryAdvanced(List<OAuth2UserInfoExtractorStrategy> extractorsList){
        return null;
    }
}
