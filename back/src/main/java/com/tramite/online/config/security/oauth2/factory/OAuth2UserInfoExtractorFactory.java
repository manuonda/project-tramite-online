
package com.tramite.online.config.security.oauth2.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tramite.online.config.security.oauth2.service.OAuth2UserInfoExtractor;
import com.tramite.online.config.security.oauth2.service.ProviderAwareOAuth2UserInfoExtractor;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OAuth2UserInfoExtractorFactory {

    private final Map<String, ProviderAwareOAuth2UserInfoExtractor> extractors;

    public OAuth2UserInfoExtractorFactory(List<ProviderAwareOAuth2UserInfoExtractor> extractorsList) {
        Map<String, ProviderAwareOAuth2UserInfoExtractor> extractorsMap = new HashMap<>();
        
        for (ProviderAwareOAuth2UserInfoExtractor extractor : extractorsList) {
            if (extractor instanceof OAuth2UserInfoExtractor) {
                ProviderAwareOAuth2UserInfoExtractor typedExtractor = (ProviderAwareOAuth2UserInfoExtractor) extractor;
                String providerValue = typedExtractor.getProviderType().getValue();
                extractorsMap.put(providerValue, extractor);
                log.info("Registered extractor for provider : {}", providerValue );
            }
        }
        
        this.extractors = Collections.unmodifiableMap(extractorsMap);
        log.info("OAuth2UserInfoExtractors initialized with providers: {}", extractors.keySet());
    }

    public ProviderAwareOAuth2UserInfoExtractor getExtractor(String registrationId) {
        return extractors.get(registrationId);
    }
    
    public Map<String, ProviderAwareOAuth2UserInfoExtractor> getAllExtractors() {
        return extractors;
    }
}