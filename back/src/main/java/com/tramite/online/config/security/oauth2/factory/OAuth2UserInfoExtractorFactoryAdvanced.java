
package com.tramite.online.config.security.oauth2.factory;

import java.util.List;
import java.util.Map;

import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.stereotype.Component;

import com.tramite.online.config.security.oauth2.service.OAuth2UserInfoExtractor;

import lombok.extern.slf4j.Slf4j;

package com.tramite.online.config.security.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

import com.tramite.online.config.security.model.TypeProvider;
import com.tramite.online.config.security.service.extractor.OAuth2UserInfoExtractor;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OAuth2UserInfoExtractorFactoryAdvanced {

    private final Map<String, OAuth2UserInfoExtractorStrategy> extractors;

    public OAuth2UserInfoExtractorFactory(List<OAuth2UserInfoExtractorStrategy> extractorsList) {
        Map<String, OAuth2UserInfoExtractorStrategy> extractorsMap = new HashMap<>();
        
        for (OAuth2UserInfoExtractorStrategy extractor : extractorsList) {
            if (extractor instanceof OAuth2UserInfoExtractor) {
                OAuth2UserInfoExtractor typedExtractor = (OAuth2UserInfoExtractor) extractor;
                extractorsMap.put(typedExtractor.getProviderType().getValue(), extractor);
            }
        }
        
        this.extractors = Collections.unmodifiableMap(extractorsMap);
        log.info("OAuth2UserInfoExtractors initialized with providers: {}", extractors.keySet());
    }

    public OAuth2UserInfoExtractorStrategy getExtractor(String registrationId) {
        return extractors.get(registrationId);
    }
    
    public Map<String, OAuth2UserInfoExtractorStrategy> getAllExtractors() {
        return extractors;
    }
}