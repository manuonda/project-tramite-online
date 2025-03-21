package com.tramite.online.config.security.service;

import com.tramite.online.config.security.model.TypeProvider;

/**
 * Extended interface for OAuth2 user info extractors that can identify their provider type.
 * This extends the basic strategy interface to add provider type identification capability.
 */
public interface OAuth2UserInfoExtractor extends OAuth2UserInfoExtractorStrategy {
    
    /**
     * Gets the type of provider this extractor handles
     * @return The provider type
     */
    TypeProvider getProviderType();
}
