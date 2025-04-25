
package com.tramite.online.config.security.oauth2.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tramite.online.config.security.oauth2.service.OAuth2UserInfoExtractor;
import com.tramite.online.config.security.oauth2.service.ProviderAwareOAuth2UserInfoExtractor;


/**
 * Factory class for managing and providing instances of  {@link ProviderAwareOAuth2UserInfoExtractor}.
 * Initializes and registers extractors for different OAuth2 providers, 
 * ensuring efficient retrieval based on provider type. 
 */

@Component
public class OAuth2UserInfoExtractorFactory {

    private final Map<String, ProviderAwareOAuth2UserInfoExtractor> extractors;
    private final Logger logger = LoggerFactory.getLogger(OAuth2UserInfoExtractor.class);


    public OAuth2UserInfoExtractorFactory(List<ProviderAwareOAuth2UserInfoExtractor> extractorsList) {
        Map<String, ProviderAwareOAuth2UserInfoExtractor> extractorsMap = new HashMap<>();
        
        for (ProviderAwareOAuth2UserInfoExtractor extractor : extractorsList) {
            if (extractor instanceof OAuth2UserInfoExtractor) {
                ProviderAwareOAuth2UserInfoExtractor typedExtractor = (ProviderAwareOAuth2UserInfoExtractor) extractor;
                String providerValue = typedExtractor.getProviderType().getValue();
                extractorsMap.put(providerValue, extractor);
                logger.info("Registered extractor for provider : {}", providerValue );
            }
        }
        
        this.extractors = Collections.unmodifiableMap(extractorsMap);
        logger.info("OAuth2UserInfoExtractors initialized with providers: {}", extractors.keySet());
    }

    public ProviderAwareOAuth2UserInfoExtractor getExtractor(String registrationId) {
        return extractors.get(registrationId);
    }
    
    public Map<String, ProviderAwareOAuth2UserInfoExtractor> getAllExtractors() {
        return extractors;
    }
}